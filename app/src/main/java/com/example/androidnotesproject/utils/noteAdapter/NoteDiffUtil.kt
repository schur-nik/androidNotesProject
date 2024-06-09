package com.example.androidnotesproject.utils.noteAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.androidnotesproject.data.Note

class NoteDiffUtil : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}