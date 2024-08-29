package com.example.noteappassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteappassignment.databinding.NoteItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.NoteViewHolder>{

    ArrayList<Note> noteArrayList;
    MyViewModel myViewModel;

    editButtonHandler clickListener;

    public MyAdapter(ArrayList<Note> noteArrayList, MyViewModel myViewModel) {
        this.noteArrayList = noteArrayList;
        this.myViewModel = myViewModel;
    }

    public void setClickListener(editButtonHandler clickListener) {
        this.clickListener = clickListener;
    }

    public void setNoteArrayList(ArrayList<Note> noteArrayList) {
        this.noteArrayList = noteArrayList;

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        NoteItemBinding noteItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.note_item,
                parent,
                false
                );

        return new NoteViewHolder(noteItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currNote = noteArrayList.get(position);

        holder.noteItemBinding.setNote(currNote);

        holder.noteItemBinding.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.delete(currNote);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(noteArrayList != null) return noteArrayList.size();
        return 0;
    }


    //View Holder
    class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        NoteItemBinding noteItemBinding;
        public NoteViewHolder(@NonNull NoteItemBinding noteItemBinding) {
            super(noteItemBinding.getRoot());
            this.noteItemBinding = noteItemBinding;

            noteItemBinding.editBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null){
                clickListener.onClick(view, noteArrayList.get(getAdapterPosition()));
            }
        }
    }
}
