package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {

    @Select("SELECT filename FROM FILES WHERE userid = #{fileUserId}")
    String[] getAllFilesByUserId(Integer userId);

    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    File getFileByFileName(String fileName);

    @Delete("DELETE FROM FILES WHERE filename = #{fileName}")
    void deleteFileByFileName(String fileName);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{fileContentType}, #{fileSize}, #{fileUserId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFileAndReturnId(File file);

}
