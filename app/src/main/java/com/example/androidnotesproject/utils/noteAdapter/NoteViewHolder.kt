package com.example.androidnotesproject.utils.noteAdapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnotesproject.R
import com.example.androidnotesproject.data.Note

class NoteViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(note: Note) {
        view.findViewById<TextView>(R.id.titleItemTextView).text = note.title
        view.findViewById<TextView>(R.id.dateItemTextView).text = note.date
        view.findViewById<TextView>(R.id.messageItemTextView).text = note.message
    }

}