package pl.akademiaspecjalistowit.ecommerce.seller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.akademiaspecjalistowit.ecommerce.client.model.AddressBo;
import pl.akademiaspecjalistowit.ecommerce.user.entity.AuthorityEntity;
import pl.akademiaspecjalistowit.ecommerce.user.entity.UserEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class SellerBo {

    public SellerBo(UUID technicalId, UserEntity userEntity, String companyName, String email, AddressBo addressBo, SellerStatus sellerStatus) {
        this.technicalId = technicalId;
        this.userEntity = userEntity;
        this.companyName = companyName;
        this.email = email;
        this.addressBo = addressBo;
        this.sellerStatus = sellerStatus;
    }

    public SellerBo(String companyName, String email, AddressBo addressBo) {
        this.sellerStatus = SellerStatus.NOT_ACTIVATED;
        this.companyName = companyName;
        this.email = email;
        this.addressBo = addressBo;
    }

    private Long id;
    private UUID technicalId;
    private UserEntity userEntity;
    private String companyName;
    private String email;
    private AddressBo addressBo;
    private SellerStatus sellerStatus;

    private void grantNewTechnicalId(){
        this.technicalId = UUID.randomUUID();
    }

    public void fillSecurityData(String password, String username){
        grantNewTechnicalId();
        this.userEntity = new UserEntity(
                grantAuthorities(),
                hashPassword(password),
                username
        );
    }

    private Set<AuthorityEntity> grantAuthorities(){
        Set<AuthorityEntity> authorityEntitySet = new HashSet<>();
        authorityEntitySet.add(new AuthorityEntity(
                "ROLE_SELLER"
        ));
        return authorityEntitySet;
    }

    private String hashPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
