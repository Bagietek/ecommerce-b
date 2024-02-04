package pl.akademiaspecjalistowit.ecommerce.order;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiaspecjalistowit.ecommerce.order.entity.ClientOrderEntity;
import pl.akademiaspecjalistowit.ecommerce.order.repository.OrderSummary;
import pl.akademiaspecjalistowit.ecommerce.order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/all")
    public List<ClientOrderEntity> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/all/summaries")
    public List<OrderSummary> getAllSummaries() {return orderService.getAllSummaries();}
}
