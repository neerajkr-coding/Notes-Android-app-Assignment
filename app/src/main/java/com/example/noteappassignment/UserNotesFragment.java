package com.example.noteappassignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.noteappassignment.databinding.FragmentUserNotesBinding;

import java.util.ArrayList;
import java.util.List;

import com.example.noteappassignment.MainActivity;

public class UserNotesFragment extends Fragment implements editButtonHandler {

    String username;
    FragmentUserNotesBinding fragmentUserNotesBinding;

    ArrayList<Note> noteArrayList = new ArrayList<>();

    //database
    NoteDatabase noteDatabase;



    public UserNotesFragment(String username) {
        this.username = username;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentUserNotesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_notes, container, false);

        return fragmentUserNotesBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentUserNotesBinding.username.setText(username);

        fragmentUserNotesBinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });

        noteDatabase = NoteDatabase.getDbInstance(requireContext());


        //Setting up RecyclerView
        RecyclerView recyclerView = fragmentUserNotesBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);


        MyAdapter adapter = new MyAdapter(noteArrayList, new ViewModelProvider(this).get(MyViewModel.class), requireContext());

        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);

        fragmentUserNotesBinding.addNotebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNoteBtn();
            }
        });

        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        myViewModel.getAllNotes(username).observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {

                noteArrayList.clear();

                for(Note n: notes){
                    noteArrayList.add(n);
                }

                adapter.notifyDataSetChanged();
            }
        });



    }

    private void logOut(){

        SharedPreferences logout = requireActivity().getSharedPreferences("checkLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = logout.edit();

        editor.clear();
        editor.apply();

        Toast.makeText(requireContext(), "Logged Out", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(requireContext(), MainActivity.class);
        startActivity(i);
    }

    private void addNoteBtn(){
//        Log.v("addNote", "Button Clicked");

        setFragment(new AddNoteFragment(username));


    }

    private void setFragment(Fragment fragment){

        FragmentManager fm = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }



    @Override
    public void onClick(View view, Note n) {

        Log.v("editBtn","-"+n.getTitle()+"   -"+n.getContent()+"   -"+n.getUsername()+"  -"+n.getId());
        setFragment(new UpdateNoteFragment(n, n.getId()));

    }
}