package org.app.hw14_dev.mapper;

import org.app.hw14_dev.model.Note;
import org.app.hw14_dev.model.dto.response.NoteResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    NoteResponse toNoteResponse(Note note);
}
