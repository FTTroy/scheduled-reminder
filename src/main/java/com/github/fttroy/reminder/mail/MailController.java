package com.github.fttroy.reminder.mail;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mail-controller")
public class MailController {

    @Value("${mail.info.receiver}")
    private String receiver;

    @Value("${mail.info.subject}")
    private String subject;

    @Value("${mail.info.message}")
    private String message;

    @Autowired
    private MailService service;

    @GetMapping("/test")
    public void test() throws MessagingException {
        service.sendSimpleMessage(receiver, subject, message);
    }
}
