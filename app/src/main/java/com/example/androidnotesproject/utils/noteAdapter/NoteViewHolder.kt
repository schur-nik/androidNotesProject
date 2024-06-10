package com.example.androidnotesproject.utils.noteAdapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnotesproject.R
import com.example.androidnotesproject.data.Note

class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val titleItemTextView = view.findViewById<TextView>(R.id.titleItemTextView)
    private val dateItemTextView = view.findViewById<TextView>(R.id.dateItemTextView)
    private val messageItemTextView = view.findViewById<TextView>(R.id.messageItemTextView)

    fun bind(note: Note, onTitleClick : (note : Note) -> Unit) {
        titleItemTextView.text = note.title
        dateItemTextView.text = note.date
        messageItemTextView.text = note.message

        titleItemTextView.setOnClickListener{
            onTitleClick(note)
        }

        messageItemTextView.setOnClickListener{
            if (messageItemTextView.maxLines == 2) {
                messageItemTextView.maxLines = Int.MAX_VALUE
            } else {
                messageItemTextView.maxLines = 2
            }
        }
    }

}