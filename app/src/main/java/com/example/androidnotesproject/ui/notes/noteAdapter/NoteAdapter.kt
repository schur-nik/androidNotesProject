package com.example.androidnotesproject.ui.notes.noteAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.androidnotesproject.models.NoteItem
import com.example.androidnotesproject.databinding.ItemNoteBinding
import com.example.androidnotesproject.databinding.ItemScheduledNoteBinding

enum class NoteType(val id: Int) {
    COMMON_NOTE(1), SCHEDULED_NOTE(2)
}

class NoteAdapter(private val onTitleClick: (note: NoteItem) -> Unit) :
    ListAdapter<NoteItem, ViewHolder>(NoteDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            NoteType.COMMON_NOTE.id -> NoteViewHolder(
                ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            NoteType.SCHEDULED_NOTE.id -> ScheduledNoteViewHolder(
                ItemScheduledNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> object : ViewHolder(FrameLayout(parent.context)) {}
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(val item = getItem(position)) {
            is NoteItem.Note -> (holder as? NoteViewHolder)?.bind(item, onTitleClick)
            is NoteItem.ScheduledNote -> (holder as? ScheduledNoteViewHolder)?.bind(item, onTitleClick)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is NoteItem.Note -> NoteType.COMMON_NOTE.id
            is NoteItem.ScheduledNote -> NoteType.SCHEDULED_NOTE.id
        }
    }
}