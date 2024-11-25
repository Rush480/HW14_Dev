package org.app.hw14_dev.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(nullable = false, length = 255)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    @Override
    public String toString() {
        return "Note " + id + ": " + title + " - " + content;
    }
}
