package org.app.hw14_dev.service;


import org.app.hw14_dev.model.Note;
import org.app.hw14_dev.repository.InMemoryNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final InMemoryNoteRepository inMemoryNoteRepository;

    @Autowired
    public NoteService(InMemoryNoteRepository inMemorynoteRepository) {
        this.inMemoryNoteRepository = inMemorynoteRepository;
    }

    public List<Note> listAll() {
        return inMemoryNoteRepository.listAll();
    }

    public Note add(Note note) {
        return inMemoryNoteRepository.add(note);
    }

    public void deleteById(long id) {
        inMemoryNoteRepository.deleteById(id);
    }

    public void update(Note note) {
        inMemoryNoteRepository.update(note);
    }

    public Note getById(long id) {
        return inMemoryNoteRepository.getById(id);
    }

}
