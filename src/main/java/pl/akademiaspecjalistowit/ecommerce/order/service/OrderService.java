package pl.akademiaspecjalistowit.ecommerce.order.service;

import pl.akademiaspecjalistowit.ecommerce.order.entity.OrderEntity;
import pl.akademiaspecjalistowit.model.GetOrderByTechnicalId200Response;
import pl.akademiaspecjalistowit.model.Order;
import pl.akademiaspecjalistowit.model.SubmitOrderFromCart200Response;
import pl.akademiaspecjalistowit.model.SubmitOrderFromCartRequest;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    SubmitOrderFromCart200Response submitOrder(SubmitOrderFromCartRequest request);

    GetOrderByTechnicalId200Response getOrderByTechnicalId(String technicalId);

    List<Order> getAllOrders();
}
