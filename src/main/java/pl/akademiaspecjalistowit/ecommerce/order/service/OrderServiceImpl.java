package pl.akademiaspecjalistowit.ecommerce.order.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.cart.model.CartBo;
import pl.akademiaspecjalistowit.ecommerce.cart.service.CartService;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;
import pl.akademiaspecjalistowit.ecommerce.client.service.ClientService;
import pl.akademiaspecjalistowit.ecommerce.order.entity.OrderEntity;
import pl.akademiaspecjalistowit.ecommerce.order.model.OrderBo;
import pl.akademiaspecjalistowit.ecommerce.order.model.OrderDetailsBo;
import pl.akademiaspecjalistowit.ecommerce.order.model.OrderState;
import pl.akademiaspecjalistowit.ecommerce.order.model.ShippingMethod;
import pl.akademiaspecjalistowit.model.SubmitOrderFromCart200Response;
import pl.akademiaspecjalistowit.model.SubmitOrderFromCartRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderDataService orderDataService;
    private final ClientService clientService;
    private final CartService cartService;

    @Override
    public SubmitOrderFromCart200Response submitOrder(SubmitOrderFromCartRequest request) {
        UUID orderTechnicalId = UUID.randomUUID();
        // todo: check if account is activated
        ClientBo clientBo = clientService.getClientBoByEmail(request.getEmail());
        List<CartBo> clientCart = cartService.getClientCartBo(clientBo);
        OrderDetailsBo orderDetailsBo = createOrderDetailsBo(clientCart, request.getShippingMethod(), orderTechnicalId);
        orderDetailsBo = orderDataService.save(orderDetailsBo);
        List<OrderBo> order = createOrderBo(orderDetailsBo, clientCart);
        // todo: check funds, if not enough mark for deletion
        orderDataService.save(order);
        // todo: delete cart, change warehouse stock
        SubmitOrderFromCart200Response response = new SubmitOrderFromCart200Response();
        response.setUuid(orderTechnicalId.toString());
        return response;
    }

    private OrderDetailsBo createOrderDetailsBo(List<CartBo> cartBo, String shippingMethod, UUID technicalId){
        return new OrderDetailsBo(
                technicalId,
                new Date(),
                null,
                ShippingMethod.valueOf(shippingMethod),
                OrderState.RECEIVED
        );
    }

    private List<OrderBo> createOrderBo(OrderDetailsBo orderDetailsBo, List<CartBo> clientCart){
        return clientCart.stream()
                .map(cartBo -> new OrderBo(
                        orderDetailsBo.getId(),
                        orderDetailsBo.getTechnicalId(),
                        cartBo.getItemsBo(),
                        Math.toIntExact(cartBo.getNumberOfProducts()),
                        cartBo.getClientBo(),
                        orderDetailsBo
                ))
                .toList();
    }
}
