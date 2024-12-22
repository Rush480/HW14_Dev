package org.app.hw14_dev.controller;

import lombok.RequiredArgsConstructor;
import org.app.hw14_dev.mapper.NoteMapper;
import org.app.hw14_dev.model.dto.request.NoteRequest;
import org.app.hw14_dev.model.dto.response.NoteResponse;
import org.app.hw14_dev.service.NoteService;
import org.app.hw14_dev.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;
    private final NoteMapper noteMapper;


    @PostMapping // POST -> http://localhost:8080/api/v1/notes
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponse createNote(@RequestBody NoteRequest request) {
        return noteService.createNote(request);
    }

    @GetMapping // Peageble GET -> http://localhost:8080/api/v1/notes?page=0&size=10
    public Page<NoteResponse> getAllNotes(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return noteService.findAll(pageRequest);
    }

    @DeleteMapping("/{id}") // DELETE -> http://localhost:8080/api/v1/notes/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@securityService.isUserMatchesPrincipal(#id)")
    public String deleteNoteById(@PathVariable Long id) {
        noteService.deleteById(id);
        return "Note deleted";
    }

    @PutMapping(value = "/{id}") // PUT -> http://localhost:8080/api/v1/notes/{id}
    @PreAuthorize("@securityService.isUserMatchesPrincipal(#id)")
    public NoteResponse editNoteById(@PathVariable Long id,
                                     @RequestBody NoteRequest noteRequest) {
        return noteService.update(noteRequest, id);
    }
}
