package com.github.fttroy.reminder.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping("/")
    public int ping() {
        log.info("pinged at {}", LocalDateTime.now());
        return 1;
    }
}
