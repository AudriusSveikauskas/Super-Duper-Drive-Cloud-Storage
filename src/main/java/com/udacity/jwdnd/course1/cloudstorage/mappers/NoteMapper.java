package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES")
    Note[] getAllNotes();

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Note getNoteById(Integer noteId);

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    Note[] getAllNotesByUserId(Integer userId);

    @Update("UPDATE NOTES SET notetitle = #{title}, notedescription = #{description} WHERE noteid = #{noteId}")
    void updateNoteByNoteId(Integer noteId, String title, String description);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteNoteByNoteId(Integer noteId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNoteAndReturnId(Note note);





























}
