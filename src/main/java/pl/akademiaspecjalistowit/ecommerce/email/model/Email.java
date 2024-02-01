package pl.akademiaspecjalistowit.ecommerce.email.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.ecommerce.email.exception.EmailValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor
public class Email {
    private final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    public Email(String email){
        validation(email);
        this.email = email;
    }

    private String email;

    private void validation(String email){
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            throw new EmailValidationException();
        }
    }
}
