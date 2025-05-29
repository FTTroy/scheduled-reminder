package com.github.fttroy.reminder.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@RestController
@RequestMapping("/ping")
public class PingController {
    private static final String ROME_ZONE_ID = "Europe/Rome";

    @GetMapping("/")
    public int ping() {
        log.info("pinged at {}", ZonedDateTime.now(ZoneId.of(ROME_ZONE_ID)).toLocalDateTime());
        return 1;
    }
}
