package pl.akademiaspecjalistowit.ecommerce.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiaspecjalistowit.api.OrderApi;
import pl.akademiaspecjalistowit.ecommerce.order.service.OrderService;
import pl.akademiaspecjalistowit.model.GetOrderByTechnicalId200Response;
import pl.akademiaspecjalistowit.model.Order;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class OrderController implements OrderApi {
    private final OrderService orderService;

    @Override
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> response = orderService.getAllOrders();
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<GetOrderByTechnicalId200Response> getOrderByTechnicalId(String technicalId) {
        GetOrderByTechnicalId200Response response = orderService.getOrderByTechnicalId(technicalId);
        return ResponseEntity
                .ok()
                .body(response);
    }
}
