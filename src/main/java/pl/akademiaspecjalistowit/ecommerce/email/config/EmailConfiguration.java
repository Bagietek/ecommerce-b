package pl.akademiaspecjalistowit.ecommerce.email.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
public class EmailConfiguration {
    private final ObjectMapper objectMapper;
    @Value("${app.credentialsPath}")
    private String credentialsFilePath;

    public EmailConfiguration(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        Map<String, String> credentialsMap = readCredentials();
        return createJavaMailSender(
                credentialsMap.get("password"),
                credentialsMap.get("username")
        );
    }

    private JavaMailSenderImpl createJavaMailSender(String password, String name){
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

    private Map<String, String> readCredentials(){
        Map<String, String> credMap = new HashMap<>();
        ClassLoader classLoader = getClass().getClassLoader();
        URL resourceUrl = classLoader.getResource(credentialsFilePath);
        try (InputStream inputStream = new FileInputStream(resourceUrl.getPath())){
            JsonNode jsonNode = objectMapper.readTree(inputStream);
            credMap.put("username",jsonNode.get("username").asText());
            credMap.put("password",jsonNode.get("password").asText());
        } catch (IOException e) {
            e.getCause();
        }
        return credMap;
    }

}
