package com.github.fttroy.reminder.mongo.document;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "history")
public class History {
    @Id
    private ObjectId id;
    private List<LocalDateTime> confirm;
    private String email;
}
