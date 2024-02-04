package pl.akademiaspecjalistowit.ecommerce.order.service;

import pl.akademiaspecjalistowit.ecommerce.order.entity.ClientOrderEntity;
import pl.akademiaspecjalistowit.ecommerce.order.repository.OrderSummary;

import java.util.List;

public interface OrderService {

    public List<ClientOrderEntity> getAllOrders();

    public List<OrderSummary> getAllSummaries();
}
