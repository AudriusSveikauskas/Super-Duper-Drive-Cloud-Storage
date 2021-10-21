package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username) {
        if (userMapper.getUserByUserUsername(username) == null) {
            return true;
        } else {
            return false;
        }
    }

    public int addNewUser(User user) {
        byte[] salt = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insertUserAndReturnId(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
    }

    public User getUser(Integer userId) {
        return userMapper.getUserByUserId(userId);
    }

    public User getUser(String username) {
        return userMapper.getUserByUserUsername(username);
    }

}
