package pl.akademiaspecjalistowit.ecommerce.cart.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.quartz.SchedulerException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.akademiaspecjalistowit.ecommerce.cart.cleanup.SchedulerService;
import pl.akademiaspecjalistowit.ecommerce.cart.model.CartView;
import pl.akademiaspecjalistowit.ecommerce.category.model.CategoryBo;
import pl.akademiaspecjalistowit.ecommerce.client.currency.CurrencyExchange;
import pl.akademiaspecjalistowit.ecommerce.client.currency.model.AccountCurrency;
import pl.akademiaspecjalistowit.ecommerce.client.exception.AddressValidationException;
import pl.akademiaspecjalistowit.ecommerce.client.exception.ClientValidationException;
import pl.akademiaspecjalistowit.ecommerce.client.model.AddressBo;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientStatus;
import pl.akademiaspecjalistowit.ecommerce.client.service.ClientService;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemAvailability;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemBo;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemView;
import pl.akademiaspecjalistowit.ecommerce.item.service.ItemService;
import pl.akademiaspecjalistowit.ecommerce.user.entity.AuthorityEntity;
import pl.akademiaspecjalistowit.ecommerce.user.entity.UserEntity;
import pl.akademiaspecjalistowit.ecommerce.warehouse.exception.WarehouseStockException;
import pl.akademiaspecjalistowit.ecommerce.warehouse.service.WarehouseService;
import pl.akademiaspecjalistowit.model.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
public class CartServiceTest {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private CartService cartServiceSuT;
    private SchedulerService schedulerService;
    private CartDataService cartDataServiceMock;
    private ItemService itemServiceMock;
    private ClientService clientServiceMock;
    private WarehouseService warehouseServiceMock;
    @BeforeEach
    void setup() throws SchedulerException {
        cartDataServiceMock = Mockito.mock(CartDataService.class);
        schedulerService = new SchedulerService();
        itemServiceMock = Mockito.mock(ItemService.class);
        clientServiceMock = Mockito.mock(ClientService.class);
        warehouseServiceMock = Mockito.mock(WarehouseService.class);
        cartServiceSuT = new CartServiceImpl(cartDataServiceMock,itemServiceMock,clientServiceMock,schedulerService,warehouseServiceMock);
    }

    @Test
    void should_return_client_cart_response(){
        //given
        UUID clientUUID = UUID.randomUUID();
        Mockito.when(cartDataServiceMock.getClientCartView(clientUUID)).thenReturn(getValidCartViewList());
        //when
        GetAllCartItems200Response response = cartServiceSuT.getClientCart(clientUUID.toString());
        //then
        verifyAllCartItemsResponse(response);
    }

    @Test
    void should_add_item_to_cart(){
        //given
        UUID clientUUID = UUID.randomUUID(), itemUUID = UUID.randomUUID();
        ItemBo item = getValidItemBo(itemUUID);
        AddItemsToCartRequest addItemsToCartRequest = getValidAddItemsRequest(clientUUID, itemUUID);
        Mockito.when(itemServiceMock.findItemByTechId(itemUUID)).thenReturn(item);
        Mockito.when(warehouseServiceMock.getWarehouseNumberOfProducts(item)).thenReturn(10L);
        Mockito.when(clientServiceMock.findByTechnicalId(clientUUID)).thenReturn(getValidClientBo(clientUUID));
        //when
        cartServiceSuT.addItem(addItemsToCartRequest);
        //then
        verifySaveInputForAddItem();
    }

    @Test
    void should_throw_warehouse_stock_exception_when_adding_item(){
        //given
        UUID clientUUID = UUID.randomUUID(), itemUUID = UUID.randomUUID();
        ItemBo item = getValidItemBo(itemUUID);
        AddItemsToCartRequest addItemsToCartRequest = getValidAddItemsRequest(clientUUID, itemUUID);
        Mockito.when(itemServiceMock.findItemByTechId(itemUUID)).thenReturn(item);
        Mockito.when(warehouseServiceMock.getWarehouseNumberOfProducts(item)).thenReturn(1L);
        Mockito.when(clientServiceMock.findByTechnicalId(clientUUID)).thenReturn(getValidClientBo(clientUUID));
        //when&then
        assertThrows(WarehouseStockException.class, () -> cartServiceSuT.addItem(addItemsToCartRequest));
    }

    private void verifySaveInputForAddItem(){
        verify(cartDataServiceMock).save(argThat(cartBo -> {
                    ItemBo item = cartBo.getItemsBo();
                    assertNull(cartBo.getPromoCode());
                    assertEquals(5L, cartBo.getNumberOfProducts());
                    assertEquals("TestName", item.getName());
                    assertEquals(ItemAvailability.AVAILABLE, item.getItemAvailability());
                    assertEquals(new BigDecimal("4.99"), item.getPrice());
                    assertEquals("TestDescription" ,item.getDescription());
                    assertEquals("TestCategory" ,item.getCategoryBo().getName());
                    return true;
                })
        );
    }

    private ClientBo getValidClientBo(UUID clientUUID) {
        return new ClientBo(
                0L,
                clientUUID,
                AccountCurrency.PLN,
                "100",
                ClientStatus.ACTIVATED,
                "TestEmail@gmail.com",
                "TestName",
                "TestSurname",
                getValidAddressBo(),
                getValidEmptyUserEntity()
        );
    }

    private UserEntity getValidEmptyUserEntity(){
        return new UserEntity(
                null,
                "TestEmail@gmail.com",
                passwordEncoder.encode("TestPassword")
        );
    }

    private AddressBo getValidAddressBo() {
        return new AddressBo(
                0L,
                "TestCity",
                "TestStreet",
                "11-111",
                "1",
                null
        );
    }

    private ItemBo getValidItemBo(UUID itemUUID) {
        return new ItemBo(
                0L,
                itemUUID,
                "TestDescription",
                new CategoryBo(0L,"TestCategory"),
                "TestName",
                ItemAvailability.AVAILABLE,
                new BigDecimal("4.99")
        );
    }

    private AddItemsToCartRequest getValidAddItemsRequest(UUID clientUUID, UUID itemUUID) {
        AddItemsToCartRequest request = new AddItemsToCartRequest();
        request.setAmount(5);
        request.setItemTechnicalId(itemUUID.toString());
        request.setClientTechnicalId(clientUUID.toString());
        return request;
    }

    private void verifyAllCartItemsResponse(GetAllCartItems200Response response) {
        Cart cart = response.getCart();
        List<Item> items = cart.getItems();
        AtomicInteger i = new AtomicInteger(1);
        assertEquals("TestCode", cart.getPromoCode());
        items.forEach(
                item -> {
                    int j = i.getAndIncrement();
                    assertEquals("TestName" + j, item.getName());
                    assertEquals("TestDescription" + j, item.getDescription());
                    assertEquals(new BigDecimal(j), item.getPrice());
                    assertEquals("TestCategory" + j, item.getCategory());
                    assertEquals(j, item.getAmount());
                }
        );
    }

    private List<CartView> getValidCartViewList() {
        return IntStream.range(1,6)
                .mapToObj(i -> new CartView(
                        "TestName" + i,
                        "TestDescription" + i,
                        BigDecimal.valueOf(i),
                        "TestCategory" + i,
                        i,
                        "TestCode"
                ))
                .collect(Collectors.toList());
    }
}
