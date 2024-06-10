package com.example.androidnotesproject.utils.noteAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.androidnotesproject.R
import com.example.androidnotesproject.data.Note

class NoteAdapter(private val onTitleClick : (note : Note) -> Unit) : ListAdapter<Note, NoteViewHolder>(NoteDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position), onTitleClick)
//        holder.itemView.setOnClickListener {
//            onNoteClick.onClick(getItem(position))
//        }
    }
}