package com.github.fttroy.reminder.mail;

import com.github.fttroy.reminder.mongo.document.History;
import com.github.fttroy.reminder.mongo.service.HistoryService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class MailService {

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private HistoryService historyService;

    public void sendSimpleMessage(String receiver, String subject, String text) throws MessagingException {
        log.info("START - sending email to {}", receiver);
        History history = historyService.findHistoryByEmail(receiver);
        boolean isConfirmedToday = false;
        if (history != null && !history.getConfirm().isEmpty()) {
            isConfirmedToday = history.getConfirm().stream().anyMatch(
                    confirm -> LocalDate.now().equals(confirm.toLocalDate())
            );
        }
        log.info("confirmed today:{}", isConfirmedToday);
        if (!isConfirmedToday) {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(text, true);
            log.info("sending mail:{}", message);
            mailSender.send(message);
            log.info("END - sending mail");
        }
    }
}
