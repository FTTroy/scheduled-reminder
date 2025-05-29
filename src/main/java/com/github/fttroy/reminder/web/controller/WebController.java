package com.github.fttroy.reminder.web.controller;


import com.github.fttroy.reminder.mongo.document.History;
import com.github.fttroy.reminder.mongo.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.Optional;

@Controller
public class WebController {

    @Value("${mail.info.receiver}")
    private String receiver;

    @Autowired
    private HistoryService service;

    @GetMapping("/")
    public String homePage(Model model) {
        Optional<History> historyOpt = Optional.ofNullable(service.findHistoryByEmail(receiver));
        model.addAttribute("confirmList", historyOpt.isPresent() ? historyOpt.get().getConfirm() : Collections.emptyList());
        return "index";
    }
}
