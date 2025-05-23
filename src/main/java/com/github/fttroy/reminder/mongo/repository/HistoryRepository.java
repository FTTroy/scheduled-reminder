package com.github.fttroy.reminder.mongo.repository;

import com.github.fttroy.reminder.mongo.document.History;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepository extends MongoRepository<History, ObjectId> {
    History findByEmail(String email);
}
