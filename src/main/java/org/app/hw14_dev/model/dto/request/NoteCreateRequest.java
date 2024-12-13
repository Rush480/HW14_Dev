package org.app.hw14_dev.model.dto.request;

import lombok.Builder;
import lombok.Data;


@Builder
public record NoteCreateRequest(String title, String content) {
}
