package org.app.hw14_dev.controller;

import lombok.RequiredArgsConstructor;
import org.app.hw14_dev.model.Note;
import org.app.hw14_dev.model.dto.request.NoteCreateRequest;
import org.app.hw14_dev.model.dto.response.NoteResponse;
import org.app.hw14_dev.service.NoteService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
@RequestMapping("/api/v1/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;


//    @GetMapping("/create")
//    public String createNote(Model model) {
//        model.addAttribute("note", new Note());
//        return "note/createNote";
//    }

    @PostMapping("/create")
    public NoteResponse createNote(@RequestBody NoteCreateRequest request) {
    // TODO implement create note logic

        noteService.add(note);

    }

    @GetMapping(value = "/list")
    public String listNotes(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "note/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteNoteAndRedirect(@PathVariable("id") long noteId, RedirectAttributes redirectAttributes) {
        noteService.deleteById(noteId);
        redirectAttributes.addFlashAttribute("message", "Note deleted successfully");
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String editNotePage(@RequestParam("id") long id, Model model) {
        model.addAttribute("note", noteService.getById(id));
        return "note/editNote";
    }

    @PostMapping("/edit")
    public String editNote(@ModelAttribute("note") Note note, RedirectAttributes redirectAttributes) {
        noteService.update(note);
        redirectAttributes.addFlashAttribute("message", "Note updated successfully");
        return "redirect:/note/list";
    }
}
