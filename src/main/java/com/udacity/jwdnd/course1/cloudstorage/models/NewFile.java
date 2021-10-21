package com.udacity.jwdnd.course1.cloudstorage.models;

import org.springframework.web.multipart.MultipartFile;

public class NewFile {

    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

}
