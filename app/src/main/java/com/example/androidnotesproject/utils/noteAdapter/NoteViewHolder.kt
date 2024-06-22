package com.example.androidnotesproject.utils.noteAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.androidnotesproject.R
import com.example.androidnotesproject.data.Note
import com.example.androidnotesproject.databinding.ItemNoteBinding
import com.example.androidnotesproject.utils.toSimpleText
import java.time.LocalDate

class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note, onTitleClick: (note: Note) -> Unit) {

        note.date.let {
            when {
                LocalDate.now().equals(note.date) -> binding.root.setBackgroundResource(R.color.today_date_note_background)
                LocalDate.now().isAfter(note.date) -> binding.root.setBackgroundResource(R.color.past_date_note_background)
                else -> binding.root.setBackgroundResource(R.color.main_background)
            }
        }

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