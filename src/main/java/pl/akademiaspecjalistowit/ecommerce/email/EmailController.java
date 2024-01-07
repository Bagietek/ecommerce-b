package pl.akademiaspecjalistowit.ecommerce.email;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.akademiaspecjalistowit.ecommerce.entity.EmailEntity;
import pl.akademiaspecjalistowit.ecommerce.email.service.EmailServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private final EmailServiceImpl emailService;
    @GetMapping("/all")
    public List<EmailEntity> getAllEmails(){
        return emailService.getAllEmails();
    }

    @PostMapping("/add")
    public void addEmail(@RequestBody EmailEntity emailEntity){
        emailService.addEmail(emailEntity);
    }
}
