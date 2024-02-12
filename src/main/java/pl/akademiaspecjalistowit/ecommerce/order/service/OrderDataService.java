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
import pl.akademiaspecjalistowit.ecommerce.order.model.OrderView;
import pl.akademiaspecjalistowit.ecommerce.order.repository.OrderDetailsRepository;
import pl.akademiaspecjalistowit.ecommerce.order.repository.OrderRepository;
import pl.akademiaspecjalistowit.ecommerce.order.repository.OrderViewRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderDataService{
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderViewRepository orderViewRepository;


    @Transactional
    public void save(List<OrderBo> orderBo){
        orderRepository.saveAll(orderBo.stream()
                .map(OrderMapper::entityFromBo)
                .collect(Collectors.toSet()));
    }

    public OrderDetailsBo save(OrderDetailsBo orderDetailsBo){
        return OrderDetailsMapper.boFromEntity(orderDetailsRepository.save(OrderDetailsMapper.entityFromBo(orderDetailsBo)));
    }

    public Set<OrderView> getAllByTechnicalId(UUID technicalId){
        return orderViewRepository.getAllByTechnicalId(technicalId);
    }

    public List<OrderView> getAllOrderViews() {
        return orderViewRepository.findAll();
    }
}
