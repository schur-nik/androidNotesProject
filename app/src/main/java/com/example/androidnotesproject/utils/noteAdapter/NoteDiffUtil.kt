package com.example.androidnotesproject.utils.noteAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.androidnotesproject.data.NoteItem

class NoteDiffUtil : DiffUtil.ItemCallback<NoteItem>() {

    override fun areItemsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
        return true
    }
}