package org.app.hw14_dev.repository;


import org.app.hw14_dev.model.Note;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
//    List<Note> listAll();
//    Note add(Note note);
//    void deleteById(long id);
//    void update(Note note);
//    Note getById(long id);

}
