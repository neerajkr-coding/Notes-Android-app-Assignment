package com.example.noteappassignment;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.net.ContentHandler;

public class AddNoteClickListener {

    Context context;
    Note note;
    FragmentManager fm;

    MyViewModel myViewModel;

    public AddNoteClickListener(Context context, Note note, MyViewModel myViewModel, FragmentManager fm) {
        this.context = context;
        this.note = note;
        this.myViewModel = myViewModel;
        this.fm = fm;
    }

    public void addNoteClicked(View view){

        Log.v("addNote", "user: "+note.getUsername()+", Title: "+note.getTitle()+", Content: "+note.getContent());

        if(note.getTitle() == null || note.getContent() == null){
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }else{
            Note n = new Note(note.getUsername(), note.getTitle(), note.getContent());
            myViewModel.insert(n);
            setFragment(new UserNotesFragment(note.getUsername()));
        }

    }

    private void setFragment(Fragment fragment){
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }
}
