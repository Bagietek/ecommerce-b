package pl.akademiaspecjalistowit.ecommerce.cart.mapper;

import pl.akademiaspecjalistowit.ecommerce.cart.entity.CartEntity;
import pl.akademiaspecjalistowit.ecommerce.cart.model.CartBo;
import pl.akademiaspecjalistowit.ecommerce.cart.model.CartView;
import pl.akademiaspecjalistowit.ecommerce.client.mapper.ClientMapper;
import pl.akademiaspecjalistowit.ecommerce.item.mapper.ItemMapper;
import pl.akademiaspecjalistowit.model.Cart;
import pl.akademiaspecjalistowit.model.GetAllCartItems200Response;
import pl.akademiaspecjalistowit.model.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {


    public static CartEntity entityFromBo(CartBo cartBo){
        if (cartBo == null){
            return null;
        }
        return new CartEntity(
                cartBo.getId(),
                cartBo.getTechnicalId(),
                ItemMapper.entityFromBo(cartBo.getItemsBo()),
                cartBo.getNumberOfProducts(),
                ClientMapper.entityFromBo(cartBo.getClientBo()),
                cartBo.getPromoCode()
        );
    }

    public static CartBo boFromEntity(CartEntity cart){
        if (cart == null){
            return null;
        }

        return new CartBo(
                cart.getId(),
                cart.getTechnicalId(),
                ItemMapper.boFromEntity(cart.getItems()),
                cart.getNumberOfProducts(),
                ClientMapper.boFromEntity(cart.getClientId()),
                cart.getPromoCode()
        );
    }

    public static GetAllCartItems200Response dtoFromView(List<CartView> cartView){
        if(cartView.isEmpty()){
            return null;
        }
        GetAllCartItems200Response response = new GetAllCartItems200Response();
        Cart cart = new Cart();
        cart.setPromoCode(cart.getPromoCode());
        cart.setItems(cartView.stream()
                .map(view -> {
                    Item item = new Item();
                    item.setName(view.getName());
                    item.setCategory(view.getCategoryName());
                    item.setDescription(view.getDescription());
                    item.setPrice(BigDecimal.valueOf(view.getPrice()));
                    item.setAmount(view.getAmount());
                    return item;
                })
                .collect(Collectors.toList())
        );
        response.setCart(cart);
        return response;
    }
}
