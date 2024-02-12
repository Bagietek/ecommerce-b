package pl.akademiaspecjalistowit.ecommerce.item.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import pl.akademiaspecjalistowit.ecommerce.category.entity.CategoryEntity;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.item.exception.ItemInvalidDataException;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemAvailability;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemView;
import pl.akademiaspecjalistowit.ecommerce.item.repository.ItemRepository;
import pl.akademiaspecjalistowit.ecommerce.item.repository.ItemViewRepository;
import pl.akademiaspecjalistowit.ecommerce.warehouse.service.WarehouseService;
import pl.akademiaspecjalistowit.model.AddItemRequest;
import pl.akademiaspecjalistowit.model.Item;
import pl.akademiaspecjalistowit.model.UpdateItemPriceRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    private ItemService itemServiceSuT;
    private ItemRepository itemRepositoryMock;
    private ItemViewRepository itemViewRepositoryMock;
    private  WarehouseService warehouseService;
    @BeforeEach
    void setup(){
        itemRepositoryMock = Mockito.mock(ItemRepository.class);
        itemViewRepositoryMock = Mockito.mock(ItemViewRepository.class);
        ItemDataService itemDataService = new ItemDataService(itemRepositoryMock, itemViewRepositoryMock);
        warehouseService = Mockito.mock(WarehouseService.class);
        itemServiceSuT = new ItemServiceImpl(itemDataService, warehouseService);
    }

    @Test
    void should_update_item_price(){
        //given
        UUID itemUUID = UUID.randomUUID();
        UpdateItemPriceRequest updateItemPriceRequest = getValidPriceData(itemUUID);
        when(itemRepositoryMock.findByTechnicalId(itemUUID))
                .thenReturn(getValidItemEntity(itemUUID));
        //when
        itemServiceSuT.updateItemPrice(updateItemPriceRequest);
        //then
        verifyItemEntityAfterPriceUpdate(itemUUID);
    }

    @Test
    void should_throw_item_invalid_data_exception_when_updating_price_with_invalid_data(){
        //given
        UUID itemUUID = UUID.randomUUID();
        UpdateItemPriceRequest updateItemPriceRequest = getInvalidPriceData(itemUUID);
        when(itemRepositoryMock.findByTechnicalId(itemUUID))
                .thenReturn(getValidItemEntity(itemUUID));
        //when & then
        assertThrows(ItemInvalidDataException.class, () -> itemServiceSuT.updateItemPrice(updateItemPriceRequest));
    }

    @Test
    void should_return_item_bo_when_processing_new_item() {
        //given
        UUID sellerUUID = UUID.randomUUID();
        AddItemRequest addItemRequest = getValidItemRequest(sellerUUID);
        //when
        itemServiceSuT.registerItem(addItemRequest);
        //then
        verifyProcessNewItemInput(sellerUUID);
    }

    @Test
    void should_throw_item_invalid_data_exception_when_processing_invalid_item(){
        //given
        UUID sellerUUID = UUID.randomUUID();
        AddItemRequest addItemRequest = getInvalidItemRequest(sellerUUID);
        //when & then
        assertThrows(ItemInvalidDataException.class, () -> {
            itemServiceSuT.registerItem(addItemRequest);
        });
    }

    @Test
    void should_return_item_list_from_item_search() {
        //given
        SearchParameters searchParameters = getValidSearchParameters();
        List<ItemView> items = getValidItemViewList();
        when(itemViewRepositoryMock.itemSearch(
                    searchParameters.minPrice,
                    searchParameters.maxPrice,
                    searchParameters.category,
                    searchParameters.page
                ))
                .thenReturn(items);
        //when
        List<Item> output = itemServiceSuT.getItemsFromSearch(
                searchParameters.minPrice,
                searchParameters.maxPrice,
                searchParameters.category,
                searchParameters.getPageNumber(),
                searchParameters.getPageSize()
        );
        //then
        verifyItemSearchOutput(output);
    }

    private void verifyItemSearchOutput(List<Item> output){
        AtomicInteger i = new AtomicInteger(1);
        output.forEach(item -> {
            int j = i.getAndIncrement();
            assertEquals("TestName" + j, item.getName());
            assertEquals("TestCategory", item.getCategory());
            assertEquals("TestDescription" + j, item.getDescription());
            assertEquals(j,item.getAmount());
            assertEquals(new BigDecimal("4.99"), item.getPrice());
        });
    }

    private List<ItemView> getValidItemViewList(){
        return IntStream.range(1,6)
                .mapToObj(i -> new ItemView(
                        "TestName" + i,
                        "TestDescription" + i,
                        new BigDecimal("4.99"),
                        "TestCategory",
                        i
                ))
                .collect(Collectors.toList());
    }

    private SearchParameters getValidSearchParameters(){
        return new SearchParameters(
                new BigDecimal("0"),
                new BigDecimal("900"),
                null,
                PageRequest.of(0,25, Sort.by("name"))
        );
    }

    private void verifyProcessNewItemInput(UUID sellerUUID){
        verify(warehouseService).processNewItem(
                argThat(itemBo -> {
                    assertEquals("Lord of the rings",itemBo.getName());
                    assertEquals(new BigDecimal("9.99"),itemBo.getPrice());
                    assertEquals("Hardcover trilogy",itemBo.getDescription());
                    assertEquals("Book",itemBo.getCategoryBo().getName());
                    assertEquals(ItemAvailability.AVAILABLE,itemBo.getItemAvailability());
                    return true;
                }),
                argThat(amount ->{
                    assertEquals(amount,5);
                    return true;
                }),
                argThat(uuid -> {
                    assertEquals(uuid,sellerUUID.toString());
                    return true;
                })
        );
    }

    private AddItemRequest getValidItemRequest(UUID sellerUUID) {
        AddItemRequest addItemRequest = new AddItemRequest();
        Item item = new Item();
        item.setName("Lord of the rings");
        item.setDescription("Hardcover trilogy");
        item.setAmount(5);
        item.setPrice(new BigDecimal("9.99"));
        item.setCategory("Book");
        addItemRequest.setItem(item);
        addItemRequest.setSellerTechId(sellerUUID.toString());
        return addItemRequest;
    }

    private AddItemRequest getInvalidItemRequest(UUID sellerUUID){
        AddItemRequest addItemRequest = getValidItemRequest(sellerUUID);
        addItemRequest.getItem().setPrice(new BigDecimal("-15"));
        return addItemRequest;
    }

    private UpdateItemPriceRequest getValidPriceData(UUID item){
        UpdateItemPriceRequest updateItemPriceRequest = new UpdateItemPriceRequest();
        updateItemPriceRequest.setTechnicalId(item.toString());
        updateItemPriceRequest.setPrice("4.99");
        return updateItemPriceRequest;
    }

    private UpdateItemPriceRequest getInvalidPriceData(UUID item){
        UpdateItemPriceRequest updateItemPriceRequest = getValidPriceData(item);
        updateItemPriceRequest.setPrice("-4.99");
        return updateItemPriceRequest;
    }

    private Optional<ItemEntity> getValidItemEntity(UUID itemUUID) {
        return Optional.of(new ItemEntity(
                0L,
                itemUUID,
                "TestDescription",
                new CategoryEntity("TestCategory"),
                "TestName",
                ItemAvailability.AVAILABLE.toString(),
                BigDecimal.TEN
        ));
    }

    private void verifyItemEntityAfterPriceUpdate(UUID itemUUID){
        verify(itemRepositoryMock).save(
                argThat(item -> {
                    assertEquals(0L, item.getId());
                    assertEquals(itemUUID, item.getTechnicalId());
                    assertEquals("TestDescription", item.getDescription());
                    assertEquals("TestCategory", item.getCategoryId().getName());
                    assertEquals("TestName", item.getName());
                    assertEquals(ItemAvailability.AVAILABLE.toString(), item.getAvailability());
                    assertEquals(new BigDecimal("4.99"), item.getPrice());
                    return true;
                })
        );
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private class SearchParameters {
        BigDecimal minPrice;
        BigDecimal maxPrice;
        String category;
        PageRequest page;

        public BigDecimal getPageSize(){
            return BigDecimal.valueOf(page.getPageSize());
        }

        public BigDecimal getPageNumber(){
            return BigDecimal.valueOf(page.getPageNumber());
        }
    }

}