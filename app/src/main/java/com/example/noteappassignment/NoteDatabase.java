package com.example.noteappassignment;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    //DAO used to work with this database
    public abstract NoteDAO noteDAO();

    //Get singleton instance of the database
    public static NoteDatabase dbInstance;
    public static NoteDatabase getDbInstance(Context context) {

        if(dbInstance == null){
            dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,
                    "Note_db").
                    fallbackToDestructiveMigration().
                    build();
        }

        return dbInstance;
    }
}
