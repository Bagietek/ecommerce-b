package pl.akademiaspecjalistowit.ecommerce.order.repository;

import pl.akademiaspecjalistowit.ecommerce.entity.ItemEntity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface OrderSummary {
    UUID getTechnicalId();
    String getCurrency();
    Date orderDateTime();
    Long getTotalAmountSum();
    String getStatus();
}
