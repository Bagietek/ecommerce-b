package pl.akademiaspecjalistowit.ecommerce.cart.cleanup;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.cart.service.CartService;
import pl.akademiaspecjalistowit.ecommerce.cart.service.CartServiceImpl;

import java.util.UUID;

@NoArgsConstructor
public class CartCleanupJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        CartService cartService = (CartService) jobDataMap.get("cartService");
        cartService.deleteClientCart(UUID.fromString(jobDataMap.getString("clientTechnicalId")));
    }
}
