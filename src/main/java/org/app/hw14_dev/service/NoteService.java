package org.app.hw14_dev.service;

import org.app.hw14_dev.exception.DatabaseException;
import org.app.hw14_dev.model.Note;
import org.app.hw14_dev.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;


import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final Random random = new Random();

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    public Note add(Note note) {
        note.setId(getRandomId());
        return noteRepository.save(note);
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

    public Note getById(long id) {
        return noteRepository.getReferenceById(id);
    }

    private long getRandomId() {
        return Math.abs(random.nextLong() * 100);
    }
}
