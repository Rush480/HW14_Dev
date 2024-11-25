package org.app.hw14_dev.service;

import org.app.hw14_dev.model.Note;
import org.app.hw14_dev.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> listAll() {
        return noteRepository.listAll();
    }

    public Note add(Note note) {
        return noteRepository.add(note);
    }

    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
        noteRepository.update(note);
    }

    public Note getById(long id) {
        return noteRepository.getById(id);
    }

}
