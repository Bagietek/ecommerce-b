package pl.akademiaspecjalistowit.ecommerce.order.repository;

import java.util.Date;
import java.util.UUID;

public interface OrderSummary {
    //todo: do it like item_summary
    UUID getTechnicalId();
    String getCurrency();
    Date orderDateTime();
    Long getTotalAmountSum();
    String getStatus();
}
