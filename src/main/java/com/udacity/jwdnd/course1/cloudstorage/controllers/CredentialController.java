package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.NewCredential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping(value = "credential")
public class CredentialController {

    private final EncryptionService encryptionService;
    private final UserService userService;
    private final CredentialService credentialService;

    public CredentialController(EncryptionService encryptionService, UserService userService, CredentialService credentialService) {
        this.encryptionService = encryptionService;
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @PostMapping(value = "add-credential")
    public String credential(
            Authentication authentication,
            @ModelAttribute("newCredential") NewCredential newCredential,
            Model model) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[16];
        secureRandom.nextBytes(key);
        if (newCredential.getCredentialId().isEmpty()) {
            credentialService.addNewCredential(
                    newCredential.getUrl(),
                    authentication.getName(),
                    newCredential.getUserName(),
                    Base64.getEncoder().encodeToString(key),
                    encryptionService.encryptValue(newCredential.getPassword(), Base64.getEncoder().encodeToString(key)));
        } else {
            Credential credential = getCredentialById(Integer.parseInt(newCredential.getCredentialId()));
            credentialService.updateCredentialById(
                    credential.getCredentialId(),
                    newCredential.getUserName(),
                    newCredential.getUrl(),
                    Base64.getEncoder().encodeToString(key),
                    encryptionService.encryptValue(newCredential.getPassword(), Base64.getEncoder().encodeToString(key)));
        }
        Integer userId = getUserId(authentication);
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("getAllCredential", credentialService.getAllCredentialsByUserId(userId));
        model.addAttribute("status", "success");
        return ("result");
    }

    @GetMapping(value = "/get-credential/{credentialId}")
    public Credential getCredentialById(@PathVariable Integer credentialId) {
        return credentialService.getCredentialById(credentialId);
    }

    @GetMapping(value = "/delete-credential/{credentialId}")
    public String deleteCredentialById(Authentication authentication,
                                       @PathVariable Integer credentialId,
                                       Model model) {
        credentialService.deleteCredentialById(credentialId);
        Integer userId = getUserId(authentication);
        model.addAttribute("getAllCredential", credentialService.getAllCredentialsByUserId(userId));
        model.addAttribute("status", "success");
        return "result";
    }

    private Integer getUserId(Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        return user.getUserId();
    }


}
