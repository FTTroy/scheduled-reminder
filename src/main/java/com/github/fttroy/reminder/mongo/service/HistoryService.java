package com.github.fttroy.reminder.mongo.service;

import com.github.fttroy.reminder.mongo.document.History;
import com.github.fttroy.reminder.mongo.repository.HistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HistoryService {

    private static final ZoneId LOCAL_ZONE = ZoneId.of("Europe/Rome");
    private static final ZoneId UTC_ZONE = ZoneId.of("UTC");

    @Autowired
    private HistoryRepository repository;

    public History findHistoryByEmail(String email) {
        Optional<History> historyOpt = repository.findByEmail(email);
        if (historyOpt.isPresent()) {
            History history = historyOpt.get();
            List<LocalDateTime> sortedConfirms = history.getConfirm()
                    .stream()
                    .sorted(Comparator.reverseOrder())
                    .toList();
            history.setConfirm(sortedConfirms);
            return history;
        }
        return null;
    }

    public History updateHistory(String email) {
        log.info("START - update history for user: {}", email);
        LocalDateTime now = ZonedDateTime.now(LOCAL_ZONE).toLocalDateTime();
        Optional<History> historyOpt = repository.findByEmail(email);
        if (historyOpt.isPresent()) {
            History historyDb = historyOpt.get();
            log.info("history for user {} found: {}", email, historyDb);
            historyDb.getConfirm().add(now);
            return repository.save(historyDb);
        } else {
            log.info("history for user {} not found", email);
            History history = new History();
            history.setConfirm(Collections.singletonList(now));
            history.setEmail(email);
            log.info("saving new history:{}", history);
            return repository.save(history);
        }
    }
}
