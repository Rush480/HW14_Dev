package org.app.hw14_dev.repository;

import lombok.AllArgsConstructor;
import org.app.hw14_dev.exception.DatabaseException;
import org.app.hw14_dev.model.Note;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.app.hw14_dev.exception.DatabaseException.NOTE_NOT_FOUND;


@AllArgsConstructor
public class NoteRepositoryImpl implements NoteRepository {
    private final Map<Long, Note> database = new HashMap<>();


    @Override
    public List<Note> listAll() {
        return List.copyOf(database.values());
    }

    @Override
    public Note add(Note note) {
        if (note.getId() == 0) {
            note.setId(randomId());
        }
        database.put(note.getId(), note);
        return note;
    }

    @Override
    public void deleteById(long id) {
        if (database.containsKey(id)) {
            database.remove(id);
        } else {
            throw new DatabaseException(NOTE_NOT_FOUND + id);
        }
    }

    @Override
    public void update(Note note) {
        if (database.containsKey(note.getId())) {
            Note existingNote = database.get(note.getId());
            existingNote.setTitle(note.getTitle());
            existingNote.setContent(note.getContent());
        } else {
            throw new DatabaseException(NOTE_NOT_FOUND + note.getId());
        }
    }

    @Override
    public Note getById(long id) {
        if (database.containsKey(id)) {
            return database.get(id);
        } else {
            throw new DatabaseException(NOTE_NOT_FOUND + id);
        }
    }

    private long randomId() {
        return Math.round(Math.random() * 1000);
    }

}
