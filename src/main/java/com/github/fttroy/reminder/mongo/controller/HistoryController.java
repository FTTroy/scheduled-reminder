package com.github.fttroy.reminder.mongo.controller;

import com.github.fttroy.reminder.mongo.document.History;
import com.github.fttroy.reminder.mongo.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/history-controller")
public class HistoryController {

    @Autowired
    HistoryService service;

    @GetMapping("/find-history-by-email")
    public History findHistoryByEmail(@RequestParam String email) {
        return service.findHistoryByEmail(email);
    }

    @PutMapping("/update-history")
    public History updateHistory(@RequestParam String email) {
        return service.updateHistory(email);
    }
}
