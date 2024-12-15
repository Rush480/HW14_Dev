package org.app.hw14_dev.controller;

import lombok.RequiredArgsConstructor;
import org.app.hw14_dev.model.dto.request.NoteRequest;
import org.app.hw14_dev.model.dto.response.NoteResponse;
import org.app.hw14_dev.service.NoteService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;


    @PostMapping                     //Done
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponse createNote(@RequestBody NoteRequest request) {
        return null;
    }

    @GetMapping(value = "/{id}") // Done
    public NoteResponse findNoteById(@PathVariable("id") Long id) {
        return noteService.findById(id);
    }

//    @GetMapping
//    public NoteResponse getAllNotes() {
//        return noteService.getAllNotes();
//    }

//    @PutMapping(value = "/{id}")
//    public NoteResponse updateNote(@PathVariable("id") Long id, @RequestBody NoteCreateRequest request) {
//        return noteService.updateNote(id, request);
//    }

    @DeleteMapping(value = "/{id}")
    public void deleteNote(@PathVariable("id") Long id) {
        noteService.deleteById(id);
    }
}
