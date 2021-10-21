package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.NewCredential;
import com.udacity.jwdnd.course1.cloudstorage.models.NewFile;
import com.udacity.jwdnd.course1.cloudstorage.models.NewNote;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    private final UserService userService;
    private final EncryptionService encryptionService;
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;

    public HomeController(UserService userService,
                          EncryptionService encryptionService,
                          FileService fileService,
                          NoteService noteService,
                          CredentialService credentialService) {
        this.userService = userService;
        this.encryptionService = encryptionService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String home(Authentication authentication,
                       @ModelAttribute("newFile") NewFile newFile,
                       @ModelAttribute("newNote") NewNote newNote,
                       @ModelAttribute("newCredential") NewCredential newCredential,
                       Model model) {
        Integer userId = getUserId(authentication);
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("getAllFiles", fileService.getAllFilesByUserId(userId));
        model.addAttribute("getAllNotes", noteService.getAllNotesByUserId(userId));
        model.addAttribute("getAllCredentials", credentialService.getAllCredentialsByUserId(userId));
        return "home";
    }

    private Integer getUserId(Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        return user.getUserId();
    }

}
