package pl.akademiaspecjalistowit.ecommerce.email.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

@Configuration
public class EmailConfiguration {
    @Bean
    public JavaMailSender getJavaMailSender() {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        URL resourceUrl = classLoader.getResource("credentials.json");
        String name = "";
        String password = "";
        try {
            JsonNode jsonNode = objectMapper.readTree(new File(resourceUrl.getPath()));
            name = jsonNode.get("username").asText();
            password = jsonNode.get("password").asText();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setPassword(password);
        mailSender.setUsername(name);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        return mailSender;
    }

}
