package pl.akademiaspecjalistowit.ecommerce.order.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.order.entity.ClientOrderEntity;
import pl.akademiaspecjalistowit.ecommerce.order.repository.OrderSummary;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderDataService orderDataService;

    public List<ClientOrderEntity> getAllOrders(){
        return orderDataService.getAllOrders();
    }

    public List<OrderSummary> getAllSummaries() {return orderDataService.getAllSummaries();}
}
