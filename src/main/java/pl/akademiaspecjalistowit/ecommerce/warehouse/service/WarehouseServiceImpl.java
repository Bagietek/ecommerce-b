package pl.akademiaspecjalistowit.ecommerce.warehouse.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemBo;
import pl.akademiaspecjalistowit.ecommerce.order.model.OrderBo;
import pl.akademiaspecjalistowit.ecommerce.seller.model.SellerBo;
import pl.akademiaspecjalistowit.ecommerce.seller.service.SellerService;
import pl.akademiaspecjalistowit.ecommerce.warehouse.exception.WarehouseAccessException;
import pl.akademiaspecjalistowit.ecommerce.warehouse.model.WarehouseBo;
import pl.akademiaspecjalistowit.model.UpdateWarehouseStockRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService{

    private final WarehouseDataService warehouseDataService;
    private final SellerService sellerService;

    public WarehouseBo getWarehouseStock(Long warehouseId) {
        return warehouseDataService.getWarehouse(warehouseId);
    }

    @Override
    public Long getWarehouseNumberOfProducts(ItemBo itemBo) {
        WarehouseBo warehouseBo = warehouseDataService.findByItem(itemBo);
        return warehouseBo.getNumberOfProducts();
    }

    @Override
    public void changeWarehouseStockByOrder(List<OrderBo> orderBos) {
        List<ItemBo> itemList = orderBos.stream()
                .map(OrderBo::getItemBo)
                .toList();
        List<Integer> amountList = orderBos.stream()
                .map(OrderBo::getAmount)
                .toList();
        List<WarehouseBo> warehouseBos = itemList.stream()
                .map(warehouseDataService::findByItem)
                .toList();
        IntStream.range(0, warehouseBos.size())
                .forEach(i -> {
                    WarehouseBo warehouseBo = warehouseBos.get(i);
                    int amount = Math.toIntExact(warehouseBo.getNumberOfProducts() - amountList.get(i));
                    warehouseBo.updateNumberOfProducts((long) amount);
                });
        warehouseDataService.updateStockByOrder(warehouseBos);
    }

    public void processNewItem(ItemBo item, Long amount, String technicalId){
        SellerBo sellerBo = sellerService.findSellerByTechId(UUID.fromString(technicalId));
        WarehouseBo warehouseBo = new WarehouseBo(
                UUID.randomUUID(),
                item,
                amount,
                sellerBo
        );
        warehouseDataService.save(warehouseBo);
    }

    @Override
    public void deleteByItem(ItemBo itemBo, String sellerTechnicalId) {
        WarehouseBo warehouseBo = warehouseDataService.findByItem(itemBo);
        if(warehouseBo.getSellerBo().getTechnicalId().toString().equals(sellerTechnicalId)){
            warehouseDataService.deleteByItem(itemBo);
            return;
        }
        throw new WarehouseAccessException();
    }

    @Override
    @Transactional
    public void updateWarehouseStock(UpdateWarehouseStockRequest updateWarehouseStockRequest) {
        WarehouseBo warehouseBo = warehouseDataService.getWarehouseByTechId(
                UUID.fromString(updateWarehouseStockRequest.getTechnicalId())
        );
        warehouseBo.updateNumberOfProducts(Long.valueOf(updateWarehouseStockRequest.getAmount()));
        warehouseDataService.save(warehouseBo);
    }
}
