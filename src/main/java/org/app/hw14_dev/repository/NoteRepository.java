package org.app.hw14_dev.repository;


import org.app.hw14_dev.model.Note;
import org.app.hw14_dev.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserId(Long userId);
    Page<Note> findNotesByUser(User user, Pageable pageable);

}
