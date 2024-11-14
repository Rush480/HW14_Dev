package org.app.hw14_dev.repository;


import org.app.hw14_dev.model.Note;

import java.util.List;

public interface NoteRepository {
    List<Note> listAll();
    Note add(Note note);
    void deleteById(long id);
    void update(Note note);
    Note getById(long id);

}
