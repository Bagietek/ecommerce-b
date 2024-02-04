package pl.akademiaspecjalistowit.ecommerce.order.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.order.entity.ClientOrderEntity;
import pl.akademiaspecjalistowit.ecommerce.order.repository.OrderRepository;
import pl.akademiaspecjalistowit.ecommerce.order.repository.OrderSummary;
import pl.akademiaspecjalistowit.ecommerce.order.repository.OrderSummaryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderDataService{
    private final OrderRepository orderRepository;
    private final OrderSummaryRepository orderSummaryRepository;

    public List<ClientOrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<OrderSummary> getAllSummaries() {return orderSummaryRepository.findSummaries();}
}
