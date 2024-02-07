package pl.akademiaspecjalistowit.ecommerce.cart.service;

import lombok.AllArgsConstructor;
import org.quartz.*;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.cart.cleanup.CartCleanupJob;
import pl.akademiaspecjalistowit.ecommerce.cart.cleanup.SchedulerService;
import pl.akademiaspecjalistowit.ecommerce.cart.exception.CartCleanupException;
import pl.akademiaspecjalistowit.ecommerce.cart.mapper.CartMapper;
import pl.akademiaspecjalistowit.ecommerce.cart.model.CartBo;
import pl.akademiaspecjalistowit.ecommerce.cart.model.CartView;
import pl.akademiaspecjalistowit.ecommerce.client.exception.ClientNotFoundException;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;
import pl.akademiaspecjalistowit.ecommerce.client.service.ClientService;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemBo;
import pl.akademiaspecjalistowit.ecommerce.item.service.ItemService;
import pl.akademiaspecjalistowit.ecommerce.warehouse.exception.WarehouseStockException;
import pl.akademiaspecjalistowit.ecommerce.warehouse.service.WarehouseService;
import pl.akademiaspecjalistowit.model.AddItemsToCartRequest;
import pl.akademiaspecjalistowit.model.GetAllCartItems200Response;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService{
    private final CartDataService cartDataService;
    private final ItemService itemService;
    private final ClientService clientService;
    private final SchedulerService schedulerService;
    private final WarehouseService warehouseService;
    private final int TIME_BEFORE_DELETION_IN_SECONDS = 60 * 15;
    // todo: scheduling for deletion upon application start


    @Override
    public GetAllCartItems200Response getClientCart(String technicalId) {
        List<CartView> cartViewList = cartDataService.getClientCartView(UUID.fromString(technicalId));
        return CartMapper.dtoFromView(cartViewList);
    }

    @Override
    public List<CartBo> getClientCartBo(ClientBo clientBo) {
        return cartDataService.getClientCartBo(clientBo);
    }

    @Override
    public void addItem(AddItemsToCartRequest addItemsToCartRequest) {
        ItemBo itemBo = itemService.findItemByTechId(UUID.fromString(addItemsToCartRequest.getItemTechnicalId()));
        checkAvailableStock(itemBo, addItemsToCartRequest.getAmount());
        ClientBo clientBo = clientService.findByTechnicalId(UUID.fromString(addItemsToCartRequest.getClientTechnicalId()));
        // promo codes not implemented
        cartDataService.save(createCart(itemBo, addItemsToCartRequest.getAmount(), clientBo, null));
        // schedule or refresh time for deletion of client cart
        scheduleDeletion(clientBo.getTechnicalId());
    }

    @Override
    public void deleteItem(UUID technicalId) {
        cartDataService.deleteByTechnicalId(technicalId);
    }

    @Override
    public void deleteClientCart(UUID clientTechnicalId) {
        cartDataService.deleteAllByClient(clientService.findByTechnicalId(clientTechnicalId));
    }
    @Override
    public void deleteClientCart(List<CartBo> cartBos) {
        ClientBo clientBo = cartBos.stream()
                .map(CartBo::getClientBo)
                .findFirst()
                .orElseThrow(ClientNotFoundException::new);
        cartDataService.deleteAllByClient(clientBo);
    }

    private void scheduleDeletion(UUID clientTechnicalId) {
        checkForRunningCleanupJob(clientTechnicalId);
        JobDetail jobDetail = createJobDetail(clientTechnicalId);
        Trigger trigger = createTrigger(clientTechnicalId);
        try{
            schedulerService.getScheduler().scheduleJob(jobDetail, trigger);
            schedulerService.getScheduler().start();
        }catch (SchedulerException e){
            throw new CartCleanupException();
        }
    }

    private void checkForRunningCleanupJob(UUID clientTechnicalId){
        JobKey jobKey = new JobKey(clientTechnicalId.toString());
        try{
            schedulerService.getScheduler().deleteJob(jobKey);
        }catch (SchedulerException e){
            throw new CartCleanupException();
        }
    }

    private void checkAvailableStock(ItemBo item, Integer clientRequest){
        Integer amount = Math.toIntExact(warehouseService.getWarehouseNumberOfProducts(item));
        amount -= cartDataService.getReservedAmount(item);
        if(clientRequest > amount) throw new WarehouseStockException();
    }

    private CartBo createCart(ItemBo item, Integer amount, ClientBo clientBo, String promoCode){
        return new CartBo(
                UUID.randomUUID(),
                item,
                Long.valueOf(amount),
                clientBo,
                promoCode
        );
    }

    private JobDetail createJobDetail(UUID clientTechnicalId){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("clientTechnicalId", clientTechnicalId.toString());
        jobDataMap.put("cartService", this);
        return JobBuilder.newJob(CartCleanupJob.class)
                .withIdentity(clientTechnicalId.toString())
                .usingJobData(jobDataMap)
                .build();
    }

    private Trigger createTrigger(UUID clientTechnicalId){
        return TriggerBuilder.newTrigger()
                .withIdentity("cartCleanupTrigger", clientTechnicalId.toString())
                .startAt(DateBuilder.futureDate(TIME_BEFORE_DELETION_IN_SECONDS, DateBuilder.IntervalUnit.SECOND))
                .build();
    }


}
