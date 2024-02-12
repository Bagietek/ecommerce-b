package pl.akademiaspecjalistowit.ecommerce.integration;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.akademiaspecjalistowit.ecommerce.client.model.AddressBo;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemAvailability;
import pl.akademiaspecjalistowit.ecommerce.item.repository.ItemRepository;
import pl.akademiaspecjalistowit.ecommerce.item.service.ItemService;
import pl.akademiaspecjalistowit.ecommerce.seller.entity.SellerEntity;
import pl.akademiaspecjalistowit.ecommerce.seller.mapper.SellerMapper;
import pl.akademiaspecjalistowit.ecommerce.seller.model.SellerBo;
import pl.akademiaspecjalistowit.ecommerce.seller.repository.SellerRepository;
import pl.akademiaspecjalistowit.ecommerce.warehouse.entity.WarehouseEntity;
import pl.akademiaspecjalistowit.ecommerce.warehouse.repository.WarehouseRepository;
import pl.akademiaspecjalistowit.model.AddItemRequest;
import pl.akademiaspecjalistowit.model.Item;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ItemIntegrationTest {

    @Autowired
    private ItemService itemServiceSuT;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private SellerRepository sellerRepository;
    @Test
    @Transactional
    public void should_register_item_successfully(){
        // given
        SellerEntity seller = getAndSaveValidSellerEntity();
        AddItemRequest addItemRequest = getValidItemRequest(seller.getTechnicalId());
        // when
        itemServiceSuT.registerItem(addItemRequest);
        // then
        WarehouseEntity warehouse = isWarehouseSavedInTheDataBase(seller);
        ItemEntity item = isItemSavedInTheDataBase(warehouse.getItemId().getTechnicalId());
        // and
        addItemRequestMatchesThoseSavedInTheDataBase(warehouse, item);
    }

    private SellerEntity getAndSaveValidSellerEntity() {
        SellerBo seller = new SellerBo(
                "TestCompanyName",
                "TestEmail",
                new AddressBo(
                        "TestCity",
                        "TestStreet",
                        "44-190",
                        "14",
                        "5"
                )
        );
        seller.fillSecurityData("TestPassword","TestUsername");
        return sellerRepository.save(SellerMapper.entityFromBo(seller));
    }

    private void addItemRequestMatchesThoseSavedInTheDataBase(WarehouseEntity warehouse, ItemEntity item) {
        assertEquals( ItemAvailability.AVAILABLE.toString(), item.getAvailability());
        assertEquals( "TestItem", item.getName());
        assertEquals("TestItem", item.getDescription());
        assertEquals("TestCategory", item.getCategoryId().getName());
        assertEquals(new BigDecimal("9.99"), item.getPrice());

        assertEquals(5, warehouse.getNumberOfProducts());
        assertEquals(item.getId(), warehouse.getItemId().getId());
    }

    private WarehouseEntity isWarehouseSavedInTheDataBase(SellerEntity sellerEntity){
        WarehouseEntity warehouse = warehouseRepository.findBySellerEntity(sellerEntity).get();
        assertThat(warehouse).isNotNull();
        return warehouse;
    }

    private ItemEntity isItemSavedInTheDataBase(UUID id){
        ItemEntity itemEntity = itemRepository.findByTechnicalId(id).get();
        assertThat(itemEntity).isNotNull();
        return itemEntity;
    }

    private AddItemRequest getValidItemRequest(UUID sellerUUID) {
        AddItemRequest addItemRequest = new AddItemRequest();
        Item item = new Item();
        item.setName("TestItem");
        item.setDescription("TestItem");
        item.setAmount(5);
        item.setPrice(new BigDecimal("9.99"));
        item.setCategory("TestCategory");
        addItemRequest.setItem(item);
        addItemRequest.setSellerTechId(sellerUUID.toString());
        return addItemRequest;
    }
}
