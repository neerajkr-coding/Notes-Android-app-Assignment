package com.example.noteappassignment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteappassignment.databinding.FragmentAddNoteBinding;

public class AddNoteFragment extends Fragment {

    FragmentAddNoteBinding fragmentAddNoteBinding;

    AddNoteClickListener addNoteClickListener;

    String username;

    Note newNote;


    public AddNoteFragment(String username) {
        this.username = username;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentAddNoteBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_add_note,
                container,
                false);

        return fragmentAddNoteBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newNote = new Note(username);

        fragmentAddNoteBinding.setNote(newNote);

        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        addNoteClickListener = new AddNoteClickListener(requireContext(),newNote, myViewModel, requireActivity().getSupportFragmentManager());

        fragmentAddNoteBinding.setClickHandler(addNoteClickListener);

//        fragmentAddNoteBinding.getContentAddNote.setText(username);


    }


}