package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.NewNote;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "note")
public class NoteController {

    private final UserService userService;
    private final NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping(value = "add-note")
    public String note(Authentication authentication,
                       @ModelAttribute("newNote") NewNote newNote,
                       Model model) {
        Integer userId = getUserId(authentication);
        Note[] allNotes = noteService.getAllNotesByUserId(userId);
        if (noteTitleIsDuplicate(allNotes, newNote.getTitle())) {
            model.addAttribute("status", "error");
            model.addAttribute("message", "Note already available.");
        }
        else if (newNote.getNoteId().isEmpty()) {
            noteService.addNewNote(
                    newNote.getTitle(),
                    newNote.getDescription(),
                    authentication.getName());
            model.addAttribute("getAllNotes", noteService.getAllNotesByUserId(userId));
            model.addAttribute("status", "success");
        } else {
            Note note = getNoteById(Integer.parseInt(newNote.getNoteId()));
            noteService.updateNoteById(
                    note.getNoteId(),
                    newNote.getTitle(),
                    newNote.getDescription());
            model.addAttribute("getAllNotes", noteService.getAllNotesByUserId(userId));
            model.addAttribute("status", "success");
        }
        return "result";
    }

    @GetMapping(value = "/get-note/{noteId}")
    public Note getNoteById(@PathVariable Integer noteId) {
        return noteService.getNoteById(noteId);
    }

    @GetMapping(value = "/delete-note/{noteId}")
    public String deleteNoteById(Authentication authentication,
                                 @PathVariable Integer noteId,
                                 Model model) {
        noteService.deleteNoteById(noteId);
        Integer userId = getUserId(authentication);
        model.addAttribute("getAllNotes", noteService.getAllNotesByUserId(userId));
        model.addAttribute("status", "success");
        return "result";
    }

    private Integer getUserId(Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        return user.getUserId();
    }

    public boolean noteTitleIsDuplicate(Note[] allNotes, String newNoteTitle) {
        for (Note note : allNotes) {
            if (note.getNoteTitle().equals(newNoteTitle)) {
                return true;
            }
        }
        return false;
    }

}
