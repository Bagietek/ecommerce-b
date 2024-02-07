package pl.akademiaspecjalistowit.ecommerce.order.mapper;

import pl.akademiaspecjalistowit.ecommerce.client.mapper.ClientMapper;
import pl.akademiaspecjalistowit.ecommerce.item.mapper.ItemMapper;
import pl.akademiaspecjalistowit.ecommerce.order.entity.OrderEntity;
import pl.akademiaspecjalistowit.ecommerce.order.model.OrderBo;

public class OrderMapper {

    public static OrderBo boFromEntity(OrderEntity orderEntity){
        if (orderEntity == null){
            return null;
        }
        return new OrderBo(
                orderEntity.getTechnicalId(),
                ItemMapper.boFromEntity(orderEntity.getItemId()),
                Math.toIntExact(orderEntity.getTotalAmount()),
                ClientMapper.boFromEntity(orderEntity.getClientId()),
                OrderDetailsMapper.boFromEntity(orderEntity.getOrderDetailsEntity())
        );
    }

    public static OrderEntity entityFromBo(OrderBo orderBo){
        if (orderBo == null){
            return null;
        }
        return new OrderEntity(
                OrderDetailsMapper.entityFromBo(orderBo.getOrderDetailsBo()),
                orderBo.getTechnicalId(),
                ItemMapper.entityFromBo(orderBo.getItemBo()),
                Long.valueOf(orderBo.getAmount()),
                ClientMapper.entityFromBo(orderBo.getClientBo())
        );
    }
}
