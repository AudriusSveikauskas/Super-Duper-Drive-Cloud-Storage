package com.udacity.jwdnd.course1.cloudstorage.models;

public class File {

    private int fileId;
    private String fileName;
    private String fileContentType;
    private String fileSize;
    private Integer fileUserId;
    private byte[] fileData;

    public File(int fileId, String fileName, String fileContentType, String fileSize, Integer fileUserId, byte[] fileData) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileContentType = fileContentType;
        this.fileSize = fileSize;
        this.fileUserId = fileUserId;
        this.fileData = fileData;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getFileUserId() {
        return fileUserId;
    }

    public void setFileUserId(Integer fileUserId) {
        this.fileUserId = fileUserId;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

}
