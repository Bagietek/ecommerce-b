package pl.akademiaspecjalistowit.ecommerce.email.model;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode
public class EmailInput {
    private String email;
}
