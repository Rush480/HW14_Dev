package org.app.hw14_dev.model.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private List<NoteResponse> notes;
}
