package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    Credential[] getAllCredentialsByUserId(Integer userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credential getCredentialById(Integer credentialId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void deleteCredentialByCredentialId(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url = #{url}, key = #{key}, password = #{password}, username = #{username} WHERE credentialid = #{credentialId}")
    void updateCredentialByCredentialId(Integer credentialId, String username, String url, String key, String password);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertCredentialAndReturnId(Credential credential);




































}
