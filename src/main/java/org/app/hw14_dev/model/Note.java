package org.app.hw14_dev.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "note")
public class Note {
    @Id
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    @Override
    public String toString() {
        return "Note " + id + ": " + title + " - " + content;
    }
}
