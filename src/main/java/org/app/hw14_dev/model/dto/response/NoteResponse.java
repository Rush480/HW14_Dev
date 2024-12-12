package org.app.hw14_dev.model.dto.response;

public record NoteResponse(
    Long id,
    String title,
    String content
) {
}
