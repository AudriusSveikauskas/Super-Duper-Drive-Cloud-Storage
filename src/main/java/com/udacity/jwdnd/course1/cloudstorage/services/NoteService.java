package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final UserMapper userMapper;
    private final NoteMapper noteMapper;

    public NoteService(UserMapper userMapper, NoteMapper noteMapper) {
        this.userMapper = userMapper;
        this.noteMapper = noteMapper;
    }

    public Note[] getAllNotesByUserId(Integer userId) {
        return noteMapper.getAllNotesByUserId(userId);
    }

    public Note getNoteById(Integer noteId) {
        return noteMapper.getNoteById(noteId);
    }

    public void addNewNote(String title, String description, String userName) {
        Note note = new Note(
                0,
                title,
                description,
                userMapper.getUserByUserUsername(userName).getUserId());
        noteMapper.insertNoteAndReturnId(note);
    }

    public void updateNoteById(Integer noteId, String title, String description) {
        noteMapper.updateNoteByNoteId(noteId, title, description);
    }

    public void deleteNoteById(Integer noteId) {
        noteMapper.deleteNoteByNoteId(noteId);
    }

}
