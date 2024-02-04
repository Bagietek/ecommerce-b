package pl.akademiaspecjalistowit.ecommerce.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.ecommerce.client.exception.AddressValidationException;

import java.util.Optional;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressBo {
    private final String postalCodeRegexPoland = "\\d{2}-\\d{3}";
    private final String numberRegex = "^[1-9]\\d*$";

    public AddressBo(String city, String street, String postalCode, String houseNumber, String flatNumber) {
        validateAddress(postalCode,houseNumber, flatNumber);
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

    private void validateAddress(String postalCode, String houseNumber, String flatNumber){
        Pattern pattern = Pattern.compile(postalCodeRegexPoland);
        if(!pattern.matcher(postalCode).matches()){
            throw new AddressValidationException();
        }
        if(Optional.ofNullable(flatNumber).isEmpty()){
            return;
        }
        pattern = Pattern.compile(numberRegex);
        if(!pattern.matcher(houseNumber).matches() || !pattern.matcher(flatNumber).matches()){
            throw new AddressValidationException();
        }
    }
}
