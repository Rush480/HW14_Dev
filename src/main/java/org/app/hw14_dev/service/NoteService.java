package org.app.hw14_dev.service;

import lombok.AllArgsConstructor;
import org.app.hw14_dev.exception.DatabaseException;
import org.app.hw14_dev.mapper.NoteMapper;
import org.app.hw14_dev.model.Note;
import org.app.hw14_dev.model.User;
import org.app.hw14_dev.model.dto.response.NoteResponse;
import org.app.hw14_dev.repository.NoteRepository;
import org.app.hw14_dev.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final NoteMapper noteMapper;



    public NoteResponse createNote(Long userId, Note note) {
        User user = userRepository.getReferenceById(userId);
        note.setUser(user);
        Note createdNote = noteRepository.save(note);
        return noteMapper.toNoteResponse(createdNote);
    }

    public List<NoteResponse> getNotesByUserId(Long userId) {
        List<Note> notes = noteRepository.findByUserId(userId);

        return notes.stream()
                .map(noteMapper::toNoteResponse)
                .toList();
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
