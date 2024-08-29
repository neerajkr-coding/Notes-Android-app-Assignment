package com.example.noteappassignment;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class UpdateNoteClickListener {

    Context context;
    Note note;
    FragmentManager fm;

    MyViewModel myViewModel;

    int id;

    public UpdateNoteClickListener(Context context, Note note, FragmentManager fm, MyViewModel myViewModel, int id) {
        this.context = context;
        this.note = note;
        this.fm = fm;
        this.myViewModel = myViewModel;
        this.id = id;
    }

    public void updateNoteClicked(View view){

        Log.v("addNote", "user: "+note.getUsername()+", Title: "+note.getTitle()+", Content: "+note.getContent());

        if(note.getTitle() == null || note.getContent() == null){
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }else{
            Note n = new Note(note.getUsername(), note.getTitle(), note.getContent());
            n.setId(id);
            Log.v("Update","-"+n.getTitle()+"  -"+n.getContent()+"  -"+n.getUsername()+"  -"+n.getId());

            myViewModel.update(n);
            setFragment(new UserNotesFragment(note.getUsername()));

        }

    }

    private void setFragment(Fragment fragment){
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

}
