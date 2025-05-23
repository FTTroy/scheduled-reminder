package com.github.fttroy.reminder.mongo.controller;

import com.github.fttroy.reminder.mail.MailService;
import com.github.fttroy.reminder.mongo.document.History;
import com.github.fttroy.reminder.mongo.service.HistoryService;
import jakarta.mail.MessagingException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/history-controller")
public class HistoryController {

    @Value("${mail.info.receiver}")
    private String receiver;

    @Value("${mail.info.subject}")
    private String subject;

    @Value("${mail.info.message}")
    private String message;

    @Autowired
    private MailService emailService;

    @GetMapping("/test")
    public void sendScheduledEmail() throws MessagingException {
        emailService.sendSimpleMessage("troianofrancesco01@gmail.com", subject, message);
    }

    @Autowired
    HistoryService service;

    @GetMapping("/find-history-by-id")
    public History findHistoryById(@RequestParam ObjectId id) {
        return service.findHistoryById(id);
    }

    @GetMapping("/find-history-by-email")
    public History findHistoryByEmail(@RequestParam String email) {
        return service.findHistoryByEmail(email);
    }

    @PostMapping("/save-history")
    public History saveHistory() {
        return service.initializeHistory();
    }

    @PutMapping("/update-history")
    public History updateHistory(@RequestParam String email) {
        return service.updateHistory(email);
    }

}
