package com.example.noteappassignment;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @ColumnInfo(name = "note_id")
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "note_username")
    String username;

    @ColumnInfo(name = "note_title")
    String title;

    @ColumnInfo(name = "note_content")
    String content;

    public Note() {
    }

    public Note(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
