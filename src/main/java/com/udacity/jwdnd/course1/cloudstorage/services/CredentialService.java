package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    private final UserMapper userMapper;
    private final CredentialMapper credentialMapper;

    public CredentialService(UserMapper userMapper, CredentialMapper credentialMapper) {
        this.userMapper = userMapper;
        this.credentialMapper = credentialMapper;
    }

    public Credential[] getAllCredentialsByUserId(Integer userId) {
        return credentialMapper.getAllCredentialsByUserId(userId);
    }

    public Credential getCredentialById(Integer credentialId) {
        return credentialMapper.getCredentialById(credentialId);
    }

    public void addNewCredential(String url, String userName, String userNameFromCredential, String key, String password) {
        Credential credential = new Credential(
                0,
                url,
                userNameFromCredential,
                key,
                password,
                userMapper.getUserByUserUsername(userName).getUserId());
        credentialMapper.insertCredentialAndReturnId(credential);
    }

    public void updateCredentialById(Integer credentialId, String newUserNameFromCredential, String url, String key, String password) {
        credentialMapper.updateCredentialByCredentialId(
                credentialId,
                newUserNameFromCredential,
                url,
                key,
                password);
    }

    public void deleteCredentialById(Integer credentialId) {
        credentialMapper.deleteCredentialByCredentialId(credentialId);
    }


}
