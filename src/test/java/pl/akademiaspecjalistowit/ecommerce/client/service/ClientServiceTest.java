package pl.akademiaspecjalistowit.ecommerce.client.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.akademiaspecjalistowit.ecommerce.client.currency.CurrencyExchange;
import pl.akademiaspecjalistowit.ecommerce.client.currency.model.AccountCurrency;
import pl.akademiaspecjalistowit.ecommerce.client.exception.AddressValidationException;
import pl.akademiaspecjalistowit.ecommerce.client.exception.ClientValidationException;
import pl.akademiaspecjalistowit.ecommerce.client.model.AddressBo;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientStatus;
import pl.akademiaspecjalistowit.ecommerce.user.entity.AuthorityEntity;
import pl.akademiaspecjalistowit.ecommerce.user.entity.UserEntity;
import pl.akademiaspecjalistowit.model.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
public class ClientServiceTest {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private ClientDataService clientDataServiceMock;
    private ClientService clientServiceSuT;

    @BeforeEach
    void setup(){
        CurrencyExchange currencyExchange = new CurrencyExchange();
        clientDataServiceMock = Mockito.mock(ClientDataService.class);
        clientServiceSuT = new ClientServiceImpl(clientDataServiceMock, currencyExchange);
    }

    @Test
    void should_verify_client(){
        //given
        ClientBo clientBo = getValidClientBo(UUID.randomUUID());
        //when
        clientServiceSuT.verifyClient(clientBo);
        //then
        verifyClientVerification();
    }

    @Test
    void should_add_correct_amount_of_funds(){
        //given
        String email = "TestEmail@gmail.com";
        AddFundsRequest addFundsRequest = getValidAddFundsRequest(email);
        Mockito.when(clientDataServiceMock.getClientBoByEmail(email)).thenReturn(getValidClientBo(email));
        //when
        clientServiceSuT.addFounds(addFundsRequest);
        //then
        verifyUpdateClientFundsInput();
    }

    @Test
    void should_return_client_bo_when_adding_new_client() {
        //given
        RegisterClientRequest registerClientRequest = getValidClientRequest();
        //when
        clientServiceSuT.addClient(registerClientRequest);
        //then
        verifyAddClientMethodInput();
    }

    @Test
    void should_add_client_information(){
        //given
        UUID clientUUID = UUID.randomUUID();
        AddClientInformationRequest addClientInformationRequest = getValidClientInformationRequest(clientUUID);
        Mockito.when(clientDataServiceMock.getClientBoByTechnicalId(clientUUID)).thenReturn(getValidClientBo(clientUUID));
        //when
        clientServiceSuT.addInformation(addClientInformationRequest);
        //then
        verifyUpdateClientInformationInput();
    }

    @Test
    void should_throw_client_validation_exception(){
        //given
        RegisterClientRequest registerClientRequest = getInvalidEmailClientRequest();
        //when && then
        assertThrows(ClientValidationException.class, () -> clientServiceSuT.addClient(registerClientRequest));
    }

    @Test
    void should_throw_address_validation_exception(){
        //given
        RegisterClientRequest registerClientRequest = getInvalidAddressClientRequest();
        //when && then
        assertThrows(AddressValidationException.class, () -> clientServiceSuT.addClient(registerClientRequest));
    }

    private void verifyClientVerification() {
        verify(clientDataServiceMock).update(
                argThat(clientBo -> {
                    assertEquals("ROLE_CLIENT",clientBo.getUserEntityId().getAuthorities().stream().findFirst().get().getAuthority());
                    assertEquals(ClientStatus.ACTIVATED, clientBo.getStatus());
                    return true;
                })
        );
    }

    private void verifyUpdateClientInformationInput(){
        verify(clientDataServiceMock).update(
                argThat(clientBo -> {
                    assertEquals("ChangedName", clientBo.getName());
                    assertEquals("ChangedSurname", clientBo.getSurname());
                    return true;
                })
        );
    }

    private void verifyUpdateClientFundsInput() {
        verify(clientDataServiceMock).update(
                argThat(clientBo -> {
                    assertEquals("399.96", clientBo.getAccountBalance());
                    return true;
                })
        );
    }

    private ClientBo getValidClientBo(String email) {
        return new ClientBo(
                0L,
                UUID.randomUUID(),
                AccountCurrency.PLN,
                "0",
                ClientStatus.NOT_ACTIVATED,
                email,
                "TestName",
                "TestSurname",
                getValidAddressBo(),
                null
        );
    }

    private ClientBo getValidClientBo(UUID clientUUID) {
        return new ClientBo(
                0L,
                clientUUID,
                AccountCurrency.PLN,
                "0",
                ClientStatus.NOT_ACTIVATED,
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

    private AddClientInformationRequest getValidClientInformationRequest(UUID clientUUID) {
        AddClientInformationRequest information = new AddClientInformationRequest();
        information.setAddress(getValidAddress());
        information.setName("ChangedName");
        information.setSurname("ChangedSurname");
        information.setTechnicalId(clientUUID.toString());
        return information;
    }

    private void verifyAddClientMethodInput(){
        verify(clientDataServiceMock).saveClient(
                (ClientBo) argThat(client -> {
                    if(client instanceof ClientBo clientBo){
                        assertEquals("TestSurname", clientBo.getSurname());
                        assertEquals("TestName", clientBo.getName());
                        assertEquals("TestEmail@gmail.com", clientBo.getEmail());
                        assertEquals("0", clientBo.getAccountBalance());
                        assertEquals(AccountCurrency.PLN, clientBo.getAccountCurrency());
                        assertEquals(ClientStatus.NOT_ACTIVATED, clientBo.getStatus());
                        assertTrue(passwordEncoder.matches("TestPassword", clientBo.getUserEntityId().getPassword()));
                        AddressBo addressBo = clientBo.getAddressBo();
                        assertEquals("TestTown", addressBo.getCity());
                        assertEquals("TestStreet", addressBo.getStreet());
                        assertEquals("11-111", addressBo.getPostalCode());
                        assertEquals("1", addressBo.getFlatNumber());
                        assertEquals("11", addressBo.getHouseNumber());
                        return true;
                    }
                    return false;
                })
        );
    }

    private RegisterClientRequest getValidClientRequest() {
        RegisterClientRequest registerClientRequest = new RegisterClientRequest();
        Client client = new Client();
        client.setSurname("TestSurname");
        client.setName("TestName");
        client.setEmail("TestEmail@gmail.com");
        client.setPassword("TestPassword");
        client.setAddress(getValidAddress());
        registerClientRequest.setClient(client);
        return registerClientRequest;
    }

    private Address getValidAddress(){
        Address address = new Address();
        address.setPostalCode("11-111");
        address.setHouseNumber("11");
        address.setFlatNumber("1");
        address.setTown("TestTown");
        address.setStreet("TestStreet");
        return address;
    }

    private RegisterClientRequest getInvalidEmailClientRequest() {
        RegisterClientRequest request = getValidClientRequest();
        request.getClient().setEmail("InvalidEmail");
        return request;
    }

    private RegisterClientRequest getInvalidAddressClientRequest() {
        RegisterClientRequest request = getValidClientRequest();
        request.getClient().setAddress(getInvalidAddress());
        return request;
    }

    private Address getInvalidAddress() {
        Address address = getValidAddress();
        address.setPostalCode("InvalidPostalCode");
        address.setHouseNumber("-15");
        return address;
    }

    private AddFundsRequest getValidAddFundsRequest(String email) {
        AddFundsRequest addFundsRequest = new AddFundsRequest();
        addFundsRequest.setEmail(email);
        Amount amount = new Amount();
        amount.setCurrency(AccountCurrency.USD.toString());
        amount.setValue("99.99");
        addFundsRequest.setAmount(amount);
        return addFundsRequest;
    }
}
