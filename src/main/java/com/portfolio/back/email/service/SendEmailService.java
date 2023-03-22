package com.portfolio.back.email.service;
import com.portfolio.back.email.model.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class SendEmailService {
    @Autowired
    JavaMailSender javaMailSender;
    
    public void sendEmail(EmailModel email) throws Exception{
        if(email.getBody().isBlank() || email.getFrom().isBlank() || email.getName().isBlank() || email.getSubject().isBlank()||
                email.getBody().isEmpty()|| email.getFrom().isEmpty() || email.getName().isEmpty() || email.getSubject().isEmpty()){
                throw new Exception("Los campos no pueden estar vacios");
        }
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setText(email.getName());
        message.setSubject(email.getSubject());
        message.setText("NOMBRE de la persona: \n"+email.getName()+".\n \n MENSAJE: \n \n"+email.getBody()
        +".\n \n MAIL de contacto: \n"+email.getFrom());
        javaMailSender.send(message);
    }
}
