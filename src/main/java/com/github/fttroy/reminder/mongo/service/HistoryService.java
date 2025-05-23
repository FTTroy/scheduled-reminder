package com.github.fttroy.reminder.mongo.service;

import com.github.fttroy.reminder.mongo.document.History;
import com.github.fttroy.reminder.mongo.repository.HistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HistoryService {

    @Autowired
    private HistoryRepository repository;

    public History findHistoryById(ObjectId id) {
        return repository.findById(id).orElse(initializeHistory());
    }

    public History findHistoryByEmail(String email) {
        return repository.findByEmail(email);
    }

    public History initializeHistory() {
        return repository.save(new History());
    }

    public History updateHistory(String email) {
        LocalDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Rome")).toLocalDateTime();
        Optional<History> historyOpt = Optional.ofNullable(repository.findByEmail(email));
        if (historyOpt.isPresent()) {
            History historyDb = historyOpt.get();
            log.info("history found: {}", historyDb);
            historyDb.getConfirm().add(now);
            return repository.save(historyDb);
        } else {
            History history = new History();
            history.setConfirm(List.of(now));
            history.setEmail(email);
            return repository.save(new History());
        }
    }
}
