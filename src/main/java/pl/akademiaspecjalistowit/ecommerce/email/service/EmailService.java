package pl.akademiaspecjalistowit.ecommerce.email.service;

import pl.akademiaspecjalistowit.ecommerce.entity.EmailEntity;

import java.util.List;

public interface EmailService {
    public List<EmailEntity> getAllEmails();

    public void addEmail(EmailEntity emailEntity);
}
