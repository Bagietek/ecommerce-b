package pl.akademiaspecjalistowit.ecommerce.order.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.cart.model.CartBo;
import pl.akademiaspecjalistowit.ecommerce.order.entity.OrderDetailsEntity;
import pl.akademiaspecjalistowit.ecommerce.order.entity.OrderEntity;
import pl.akademiaspecjalistowit.ecommerce.order.mapper.OrderDetailsMapper;
import pl.akademiaspecjalistowit.ecommerce.order.mapper.OrderMapper;
import pl.akademiaspecjalistowit.ecommerce.order.model.OrderBo;
import pl.akademiaspecjalistowit.ecommerce.order.model.OrderDetailsBo;
import pl.akademiaspecjalistowit.ecommerce.order.repository.OrderDetailsRepository;
import pl.akademiaspecjalistowit.ecommerce.order.repository.OrderRepository;
import pl.akademiaspecjalistowit.ecommerce.order.repository.OrderViewRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderDataService{
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderViewRepository orderViewRepository;


    public void save(List<OrderBo> orderBo){
        orderBo.stream().map(OrderMapper::entityFromBo).forEach(orderRepository::save);
    }

    public OrderDetailsBo save(OrderDetailsBo orderDetailsBo){
        return OrderDetailsMapper.boFromEntity(orderDetailsRepository.save(OrderDetailsMapper.entityFromBo(orderDetailsBo)));
    }

}
