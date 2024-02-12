package pl.akademiaspecjalistowit.ecommerce.order.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.cart.model.CartBo;
import pl.akademiaspecjalistowit.ecommerce.cart.service.CartService;
import pl.akademiaspecjalistowit.ecommerce.client.currency.CurrencyExchange;
import pl.akademiaspecjalistowit.ecommerce.client.exception.ClientFundsException;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;
import pl.akademiaspecjalistowit.ecommerce.client.service.ClientService;
import pl.akademiaspecjalistowit.ecommerce.order.exception.OrderNotFoundException;
import pl.akademiaspecjalistowit.ecommerce.order.model.*;
import pl.akademiaspecjalistowit.ecommerce.warehouse.service.WarehouseService;
import pl.akademiaspecjalistowit.model.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderDataService orderDataService;
    private final ClientService clientService;
    private final CartService cartService;
    private final CurrencyExchange currencyExchange;
    private final WarehouseService warehouseService;

    @Override
    @Transactional
    public SubmitOrderFromCart200Response submitOrder(SubmitOrderFromCartRequest request) {
        // todo: reverse currency exchange: Item price into clients AccountCurrency
        UUID orderTechnicalId = UUID.randomUUID();
        ClientBo clientBo = clientService.getClientBoByEmail(request.getEmail());
        // clientOrderService
        List<CartBo> clientCart = cartService.getClientCartBo(clientBo);
        BigDecimal summedPrice = sumOrderPrice(clientCart);
        BigDecimal clientExchangedFunds = currencyExchange.exchangeIntoPln(new BigDecimal(clientBo.getAccountBalance()), clientBo.getAccountCurrency());
        // todo: automatic deletion if not enough funds
        Boolean isFunded = checkClientFunds(summedPrice, clientExchangedFunds);
        clientBo.updateClientFunds(summedPrice, clientExchangedFunds);
        OrderDetailsBo orderDetailsBo = createOrderDetailsBo(clientCart, request.getShippingMethod(), orderTechnicalId, isFunded);
        // assigned id value
        orderDetailsBo = orderDataService.save(orderDetailsBo);
        List<OrderBo> order = createOrderBo(orderDetailsBo, clientCart);
        orderDataService.save(order);
        clientService.update(clientBo);
        cartService.deleteClientCart(clientCart);
        warehouseService.changeWarehouseStockByOrder(order);
        SubmitOrderFromCart200Response response = new SubmitOrderFromCart200Response();
        response.setUuid(orderTechnicalId.toString());
        return response;
    }

    @Override
    public GetOrderByTechnicalId200Response getOrderByTechnicalId(String technicalId) {
        Set<OrderView> orderView = orderDataService.getAllByTechnicalId(UUID.fromString(technicalId));
        GetOrderByTechnicalId200Response response = new GetOrderByTechnicalId200Response();
        Order order = createOrderDto(orderView);
        response.setOrder(order);
        return response;
    }

    @Override
    public List<Order> getAllOrders() {
        List<OrderView> orderView = orderDataService.getAllOrderViews();
        Map<UUID, List<OrderView>> ordersByTechnicalId = orderView.stream()
                .collect(Collectors.groupingBy(OrderView::getTechnicalId));
        List<UUID> keys = ordersByTechnicalId.keySet().stream().toList();
        return keys.stream()
                .map(ordersByTechnicalId::get)
                .map(order -> createOrderDto(new HashSet<>(order)))
                .toList();
    }

    private Boolean checkClientFunds(BigDecimal summedOrder, BigDecimal exchangedClientFunds){
        return summedOrder.compareTo(exchangedClientFunds) <= 0;
    }

    private Order createOrderDto(Set<OrderView> orderView){
        if(orderView.isEmpty()){
            throw new OrderNotFoundException();
        }
        OrderView firstOrder = orderView.iterator().next();
        Order order = new Order();
        order.setItems(orderView.stream()
                .map(view -> {
                    Item item = new Item();
                    item.setAmount(view.getAmount());
                    item.setPrice(BigDecimal.valueOf(view.getPrice()));
                    item.setDescription(view.getItemDescription());
                    item.setName(view.getItemName());
                    return item;
                })
                .toList());
        order.setOrderDateTime(firstOrder.getOrderDate().toString());
        order.setTechnicalId(firstOrder.getTechnicalId().toString());
        order.setStatus(firstOrder.getOrderState());
        order.setShippingMethod(firstOrder.getShippingMethod());
        if(firstOrder.getDeliveryDate() != null){
            order.setDeliveryDateTime(firstOrder.getDeliveryDate().toString());
        }
        return order;
    }

    private BigDecimal sumOrderPrice(List<CartBo> clientCart){
        return clientCart
                .stream()
                .map(cart ->
                        cart.getItemsBo().getPrice()
                                .multiply(BigDecimal.valueOf(cart.getNumberOfProducts())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    private OrderDetailsBo createOrderDetailsBo(List<CartBo> cartBo, String shippingMethod, UUID technicalId, Boolean isFounded){
        OrderState orderState = OrderState.RECEIVED;
        if (!isFounded){
            orderState = OrderState.PAYMENT_REQUIRED;
        }
        return new OrderDetailsBo(
                technicalId,
                new Date(),
                null,
                ShippingMethod.valueOf(shippingMethod),
                orderState
        );
    }

    private List<OrderBo> createOrderBo(OrderDetailsBo orderDetailsBo, List<CartBo> clientCart){
        return clientCart.stream()
                .map(cartBo -> new OrderBo(
                        orderDetailsBo.getTechnicalId(),
                        cartBo.getItemsBo(),
                        Math.toIntExact(cartBo.getNumberOfProducts()),
                        cartBo.getClientBo(),
                        orderDetailsBo
                ))
                .toList();
    }
}
