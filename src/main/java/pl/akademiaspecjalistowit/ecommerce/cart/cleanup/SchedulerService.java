package pl.akademiaspecjalistowit.ecommerce.cart.cleanup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.springframework.stereotype.Service;


@Service
@Getter
public class SchedulerService{
    private final SchedulerFactory schedulerFactory;
    private final Scheduler scheduler;

    public SchedulerService() throws SchedulerException {
        schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();
    }


}
