package org.app.hw14_dev.model;

import lombok.*;
//@Entity

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    private long id;
    private String title;
    private String content;

    @Override
    public String toString() {
        return "Note " + id + ": " + title + " - " + content;
    }
}
