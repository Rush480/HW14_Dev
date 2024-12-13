package org.app.hw14_dev.service;

import lombok.AllArgsConstructor;
import org.app.hw14_dev.exception.DatabaseException;
import org.app.hw14_dev.model.Note;
import org.app.hw14_dev.model.dto.request.NoteCreateRequest;
import org.app.hw14_dev.model.dto.response.NoteResponse;
import org.app.hw14_dev.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class NoteService {

    private final NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        return notes;
    }

    public NoteResponse createNote(NoteCreateRequest noteRequest) {
        Note note = Note.builder()
                .title(noteRequest.title())
                .content(noteRequest.content())
                .build();
        noteRepository.save(note);
        return NoteResponse.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .build();
    }

    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
        if (note.getId() == 0) {
            throw new DatabaseException(DatabaseException.NOTE_NOT_FOUND + note.getId());
        }
        noteRepository.save(note);
    }

    public NoteResponse findById(long id) {
        Note note = noteRepository.getReferenceById(id);
        return NoteResponse.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .build();
    }


}
