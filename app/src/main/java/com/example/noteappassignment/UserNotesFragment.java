package com.example.noteappassignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.noteappassignment.databinding.FragmentUserNotesBinding;

public class UserNotesFragment extends Fragment {

    String username;
    FragmentUserNotesBinding fragmentUserNotesBinding;


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

        fragmentUserNotesBinding.testText.setText(username);
        fragmentUserNotesBinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
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
}