package pl.akademiaspecjalistowit.ecommerce.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressBo {

    public AddressBo(String city, String street, String postalCode, String houseNumber, String flatNumber) {
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }

    private Long id;
    private String city;
    private String street;
    private String postalCode;
    private String houseNumber;
    private String flatNumber;
}
