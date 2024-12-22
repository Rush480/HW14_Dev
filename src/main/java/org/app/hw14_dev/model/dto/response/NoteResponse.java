package org.app.hw14_dev.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NoteResponse {
    private Long id;
    private String title;
    private String content;
}
