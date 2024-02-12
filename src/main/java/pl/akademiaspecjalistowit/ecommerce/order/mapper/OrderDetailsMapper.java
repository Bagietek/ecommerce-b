package pl.akademiaspecjalistowit.ecommerce.order.mapper;

import pl.akademiaspecjalistowit.ecommerce.order.entity.OrderDetailsEntity;
import pl.akademiaspecjalistowit.ecommerce.order.model.OrderDetailsBo;
import pl.akademiaspecjalistowit.ecommerce.order.model.OrderState;
import pl.akademiaspecjalistowit.ecommerce.order.model.ShippingMethod;

public class OrderDetailsMapper {

    public static OrderDetailsBo boFromEntity(OrderDetailsEntity orderDetails){
        if (orderDetails == null){
            return null;
        }
        return new OrderDetailsBo(
                orderDetails.getId(),
                orderDetails.getTechnicalId(),
                orderDetails.getOrderDate(),
                orderDetails.getDeliveryDate(),
                ShippingMethod.valueOf(orderDetails.getShippingMethod()),
                OrderState.valueOf(orderDetails.getStatus())
        );
    }

    public static OrderDetailsEntity entityFromBo(OrderDetailsBo orderDetails){
        if (orderDetails == null){
            return null;
        }
        return new OrderDetailsEntity(
                orderDetails.getId(),
                orderDetails.getTechnicalId(),
                orderDetails.getOrderDate(),
                orderDetails.getDeliveryDate(),
                orderDetails.getShippingMethod().toString(),
                orderDetails.getOrderState().toString()
        );
    }
}
