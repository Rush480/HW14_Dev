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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_seq")
    @SequenceGenerator(name = "note_seq", sequenceName = "note_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    @Override
    public String toString() {
        return "Note " + id + ": " + title + " - " + content;
    }
}
