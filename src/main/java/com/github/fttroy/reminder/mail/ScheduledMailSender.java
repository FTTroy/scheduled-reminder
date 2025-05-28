package com.github.fttroy.reminder.mail;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledMailSender {

    @Value("${mail.info.receiver}")
    private String receiver;

    @Value("${mail.info.subject}")
    private String subject;

    @Value("${mail.info.message}")
    private String message;

    @Autowired
    private MailService emailService;

    @Scheduled(cron = "${scheduled.cron}")
    public void sendScheduledEmail() throws MessagingException {
        emailService.sendSimpleMessage(receiver, subject, message);
    }
}
