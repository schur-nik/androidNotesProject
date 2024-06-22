package com.example.androidnotesproject.utils.noteAdapter

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnotesproject.R
import com.example.androidnotesproject.data.Note
import com.example.androidnotesproject.databinding.ItemNoteBinding
import com.example.androidnotesproject.utils.DATE_FORMAT
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId

class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note, onTitleClick: (note: Note) -> Unit) {

        note.date?.let {
            if (LocalDate.now().equals(
                    note.date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                )
            ) {
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.today_date_note_background
                    )
                )
            } else if (LocalDate.now().isAfter(
                    note.date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                )
            ) {
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.past_date_note_background
                    )
                )
            } else {
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.main_background
                    )
                )
            }
        }

        binding.run {

            titleItemTextView.text = note.title
            dateItemTextView.text = note.date?.let { SimpleDateFormat(DATE_FORMAT).format(it) }
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