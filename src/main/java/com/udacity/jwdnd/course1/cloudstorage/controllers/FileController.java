package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.NewFile;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

@Controller
@RequestMapping(value = "file")
public class FileController {

    private final EncryptionService encryptionService;
    private final UserService userService;
    private final FileService fileService;

    public FileController(EncryptionService encryptionService, UserService userService, FileService fileService) {
        this.encryptionService = encryptionService;
        this.userService = userService;
        this.fileService = fileService;
    }

    @PostMapping(value = "add-file")
    public String file(
            Authentication authentication,
            @ModelAttribute("newFile") NewFile newFile,
            Model model) throws IOException {
        Integer userId = getUserId(authentication);
        String[] allFiles = fileService.getAllFilesByUserId(userId);
        MultipartFile multipartFile = newFile.getMultipartFile();
        String fileName = multipartFile.getOriginalFilename();
        if (!fileIsDuplicate(allFiles, fileName)) {
            fileService.uploadFile(multipartFile, authentication.getName());
            model.addAttribute("status", "success");
        } else {
            model.addAttribute("status", "error");
            model.addAttribute("message", "File already exists.");
        }
        return "result";
    }

    @GetMapping(value = "/get-file/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public byte[] file(@PathVariable String fileName) {
        return fileService.getFileByFileName(fileName).getFileData();
    }

    @GetMapping(value = "/delete-file/{fileName}")
    public String file(
            Authentication authentication,
            @PathVariable String fileName,
            Model model) {
        fileService.deleteFileByFileName(fileName);
        model.addAttribute("allFiles", fileService.getAllFilesByUserId(getUserId(authentication)));
        model.addAttribute("status", "success");
        return "result";
    }

    private Integer getUserId(Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        return user.getUserId();
    }

    public boolean fileIsDuplicate(String[] allFiles, String file) {
        for (String allFile : allFiles) {
            if (allFile.equals(file)) {
                return true;
            }
        }
        return false;
    }



}
