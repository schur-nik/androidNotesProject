package com.example.androidnotesproject.ui.notes.noteAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.androidnotesproject.models.NoteItem

class NoteDiffUtil : DiffUtil.ItemCallback<NoteItem>() {

    override fun areItemsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
        return true
    }
}