package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileService {

    private final UserMapper userMapper;
    private final FileMapper fileMapper;

    public FileService(UserMapper userMapper, FileMapper fileMapper) {
        this.userMapper = userMapper;
        this.fileMapper = fileMapper;
    }

    public String[] getAllFilesByUserId(Integer userId) {
        return fileMapper.getAllFilesByUserId(userId);
    }

    public File getFileByFileName(String fileName) {
        return fileMapper.getFileByFileName(fileName);
    }

    public void uploadFile(MultipartFile multipartFile, String userName) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[2048];
        while (inputStream.read(data, 0, data.length) != -1) {
            byteArrayOutputStream.write(data, 0, inputStream.read(data, 0, data.length));
        }

        byteArrayOutputStream.flush();

        File file = new File(
                0,
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                String.valueOf(multipartFile.getSize()),
                userMapper.getUserByUserUsername(userName).getUserId(),
                byteArrayOutputStream.toByteArray());

        fileMapper.insertFileAndReturnId(file);

    }

    public void deleteFileByFileName(String fileName) {
        fileMapper.deleteFileByFileName(fileName);
    }


}
