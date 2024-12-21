package org.app.hw14_dev.service;

import lombok.AllArgsConstructor;
import org.app.hw14_dev.exception.DatabaseException;
import org.app.hw14_dev.mapper.NoteMapper;
import org.app.hw14_dev.model.Note;
import org.app.hw14_dev.model.dto.request.NoteRequest;
import org.app.hw14_dev.model.dto.response.NoteResponse;
import org.app.hw14_dev.repository.NoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.app.hw14_dev.exception.DatabaseException.NOTE_NOT_FOUND;


@Service
@AllArgsConstructor
@Transactional
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserService userService;
    private final NoteMapper noteMapper;




    public NoteResponse createNote(NoteRequest note) {
        String username = getCurrentUserName();
        Note createdNote = Note.builder()
                .title(note.getTitle())
                .content(note.getContent())
                .user(userService.findByUserName(username))
                .build();
        noteRepository.save(createdNote);
        return noteMapper.toNoteResponse(createdNote);
    }

    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    public NoteResponse update(NoteRequest noteRequest, long id) {
     Note note = noteRepository.findById(id).orElseThrow(() -> new DatabaseException(NOTE_NOT_FOUND + id));
     note.setTitle(noteRequest.getTitle());
     note.setContent(noteRequest.getContent());
     noteRepository.save(note);
     return noteMapper.toNoteResponse(note);
    }


    public Page<NoteResponse> findAll(PageRequest pageRequest) {
       return noteRepository.findNotesByUser(userService.findByUserName(getCurrentUserName()),pageRequest)
                .map(noteMapper::toNoteResponse);
    }

    private static String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
