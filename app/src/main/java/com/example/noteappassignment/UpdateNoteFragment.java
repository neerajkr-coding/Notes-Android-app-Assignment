package com.example.noteappassignment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
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

import com.example.noteappassignment.databinding.FragmentUpdateNoteBinding;
import com.example.noteappassignment.databinding.NoteItemBinding;


public class UpdateNoteFragment extends Fragment {

    Note targetNote;
    int targetNoteid;

    FragmentUpdateNoteBinding fragmentUpdateNoteBinding;
    UpdateNoteClickListener listener;


    public UpdateNoteFragment(Note targetNote, int targetNoteid) {
        this.targetNote = targetNote;
        this.targetNoteid = targetNoteid;
    }

    public UpdateNoteFragment(){

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentUpdateNoteBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_update_note,
                container,
                false
        );

        return fragmentUpdateNoteBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Note temp = new Note(targetNote.getUsername(),targetNote.getTitle(),targetNote.getContent());

        fragmentUpdateNoteBinding.setNote(temp);

        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        listener = new UpdateNoteClickListener(requireContext(),temp, requireActivity().getSupportFragmentManager(), myViewModel, targetNoteid );

        fragmentUpdateNoteBinding.setClickHandler(listener);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                setFragment(new UserNotesFragment(targetNote.getUsername()));
            }
        });

    }

    private void setFragment(Fragment fragment){

        FragmentManager fm = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }


}