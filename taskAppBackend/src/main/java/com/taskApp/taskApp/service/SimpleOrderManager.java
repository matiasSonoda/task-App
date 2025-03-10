package com.taskApp.taskApp.service;

import com.taskApp.taskApp.model.Task;
import com.taskApp.taskApp.model.AppUser;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SimpleOrderManager implements TaskManager{
    
    private MailSender mailsender;
    private SimpleMailMessage templateMessage; 

    public void setMailsender(MailSender mailsender) {
        this.mailsender = mailsender;
    }
    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }
    
    
    @Override
    public void placerOrder(AppUser user, Task task) {
        
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(user.getMail());
        msg.setSubject("TEST");
        msg.setText(
                "Dear " + user.getFirstname()
                        + user.getLastname()
                        + ",email de prueba"
                        + task.getTitle());
        try{
            this.mailsender.send(msg);
        }catch(MailException ex){
            System.err.println(ex.getMessage());
        } 
    }
    
}
