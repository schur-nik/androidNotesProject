package com.example.androidnotesproject.ui.notes.noteAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.androidnotesproject.models.NoteItem
import com.example.androidnotesproject.databinding.ItemNoteBinding
import com.example.androidnotesproject.extensions.toSimpleText

class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: NoteItem.Note, onTitleClick: (note: NoteItem.Note) -> Unit) {

        binding.run {
            titleItemTextView.text = note.title
            dateItemTextView.text = note.date.toSimpleText()
            messageItemTextView.text = note.message

            titleItemTextView.setOnClickListener {
                onTitleClick(note)
            }

            messageItemTextView.setOnClickListener {
                if (messageItemTextView.maxLines == 2) {
                    messageItemTextView.maxLines = Int.MAX_VALUE
                } else {
                    messageItemTextView.maxLines = 2
                }
            }
        }
    }

}