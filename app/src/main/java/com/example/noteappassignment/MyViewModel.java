package com.example.noteappassignment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyViewModel extends AndroidViewModel {

    private final NoteDAO noteDAO;
    private ExecutorService executor;


    public MyViewModel(@NonNull Application application) {
        super(application);

        NoteDatabase db = NoteDatabase.getDbInstance(application);
        noteDAO = db.noteDAO();

        this.executor = Executors.newSingleThreadExecutor();
    }


    public LiveData<List<Note>> getAllNotes(String username){
        return noteDAO.getAllNotesForUser(username);
    }


    public void insert(Note note){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteDAO.insert(note);
            }
        });
    }

    public void update(Note note){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteDAO.update(note);
            }
        });
    }

    public void delete(Note note){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteDAO.delete(note);
            }
        });
    }

    public LiveData<Note> getNoteById(int id){
        return noteDAO.getNoteById(id);
    }


}
