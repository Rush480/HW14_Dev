package org.app.hw14_dev.model;

import lombok.Builder;
import lombok.Data;
//@Entity

@Data
@Builder
public class Note {
    private long id;
    private String title;
    private String content;
}
