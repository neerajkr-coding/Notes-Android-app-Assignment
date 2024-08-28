package com.example.noteappassignment;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM note_table WHERE note_username = :username ORDER BY note_title ASC")
    LiveData<List<Note>> getAllNotesForUser(String username);

    @Query("SELECT * FROM note_table WHERE note_id = :id")
    LiveData<Note> getNoteById(int id);
}
