package pl.akademiaspecjalistowit.ecommerce.integration;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.akademiaspecjalistowit.ecommerce.cart.entity.CartEntity;
import pl.akademiaspecjalistowit.ecommerce.cart.repository.CartRepository;
import pl.akademiaspecjalistowit.ecommerce.cart.service.CartService;
import pl.akademiaspecjalistowit.ecommerce.category.entity.CategoryEntity;
import pl.akademiaspecjalistowit.ecommerce.category.repository.CategoryRepository;
import pl.akademiaspecjalistowit.ecommerce.client.currency.model.AccountCurrency;
import pl.akademiaspecjalistowit.ecommerce.client.entity.AddressEntity;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientStatus;
import pl.akademiaspecjalistowit.ecommerce.client.repository.ClientRepository;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemAvailability;
import pl.akademiaspecjalistowit.ecommerce.item.repository.ItemRepository;
import pl.akademiaspecjalistowit.ecommerce.warehouse.entity.WarehouseEntity;
import pl.akademiaspecjalistowit.ecommerce.warehouse.repository.WarehouseRepository;
import pl.akademiaspecjalistowit.model.Cart;
import pl.akademiaspecjalistowit.model.GetAllCartItems200Response;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
public class CartIntegrationTest {
    @Autowired
    private CartService cartServiceSuT;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @BeforeEach
    void setup(){
        addItemsToDataBase();
    }
    @Test
    @Transactional
    void should_register_cart_successfully(){
        //given

        //when

        //then

    }

    @Test
    @Transactional
    void should_return_clients_cart(){
        //given
        //addItemsToDataBase();
        ClientEntity client = clientRepository.save(getValidClientEntity());
        List<CartEntity> test = cartRepository.saveAll(getValidCartEntities(client));
        //when
        GetAllCartItems200Response response = cartServiceSuT.getClientCart(client.getTechnicalId().toString());
        //then
        verifyAllCartItemsResponse(response);
    }

    private void verifyAllCartItemsResponse(GetAllCartItems200Response response) {
        Cart cart = response.getCart();
        assertNull(cart.getPromoCode());
        assertEquals(5, cart.getItems().size());
        cart.getItems().forEach(item -> {
                    assertEquals("TestDescription", item.getDescription());
                    assertEquals(new BigDecimal("9.99"), item.getPrice());
                    assertEquals(5, item.getAmount());
                }
        );
    }

    private void addItemsToDataBase() {
        List<CategoryEntity> categories = IntStream.range(1,6)
                .mapToObj(i -> new CategoryEntity(
                        (long) i,
                        "TestCategory" + i
                ))
                .toList();
        categoryRepository.saveAll(categories);
        List<ItemEntity> items = IntStream.range(1,6)
                .mapToObj(i -> new ItemEntity(
                        (long) i,
                        UUID.randomUUID(),
                        "TestDescription",
                        categories.get(i-1),
                        "TestName" + i,
                        ItemAvailability.AVAILABLE.toString(),
                        new BigDecimal("9.99")
                ))
                .collect(Collectors.toList());
        itemRepository.saveAll(items);
        Set<WarehouseEntity> warehouses = IntStream.range(1,6)
                        .mapToObj(i -> new WarehouseEntity(
                                (long) i,
                                UUID.randomUUID(),
                                items.get(i-1),
                                100L,
                                null
                        ))
                        .collect(Collectors.toSet());
        warehouseRepository.saveAll(warehouses);
    }

    private Iterable<CartEntity> getValidCartEntities( ClientEntity client) {
        return IntStream.range(1,6)
                .mapToObj(i -> new CartEntity(
                        UUID.randomUUID(),
                        itemRepository.findById((long) i).get(),
                        5L,
                        client,
                        null
                ))
                .collect(Collectors.toSet());
    }

    private ClientEntity getValidClientEntity() {
        return new ClientEntity(
                UUID.randomUUID(),
                AccountCurrency.PLN.toString(),
                "100",
                ClientStatus.ACTIVATED.toString(),
                "TestEmail@gmail.com",
                "TestName",
                "TestSurname",
                getValidAddressEntity()
        );
    }

    private AddressEntity getValidAddressEntity() {
        return new AddressEntity(
                "TestCity",
                "TestStreet",
                "11-111",
                "11",
                null
        );
    }
}
