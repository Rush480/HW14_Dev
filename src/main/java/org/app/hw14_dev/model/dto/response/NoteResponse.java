package org.app.hw14_dev.model.dto.response;

import lombok.Builder;

@Builder
public record NoteResponse(Long id, String title, String content) {
}
