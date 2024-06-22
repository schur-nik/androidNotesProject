package com.example.androidnotesproject.utils.noteAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import com.example.androidnotesproject.R
import com.example.androidnotesproject.data.Note
import com.example.androidnotesproject.databinding.ItemNoteBinding

class NoteAdapter(private val onTitleClick: (note: Note) -> Unit) :
    ListAdapter<Note, NoteViewHolder>(NoteDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position), onTitleClick)
    }
}