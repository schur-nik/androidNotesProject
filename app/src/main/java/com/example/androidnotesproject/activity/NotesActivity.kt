package com.example.androidnotesproject.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnotesproject.R
import com.example.androidnotesproject.data.Note
import com.example.androidnotesproject.data.NoteList
import com.example.androidnotesproject.utils.addNoteToList
import com.example.androidnotesproject.utils.noteAdapter.NoteAdapter
import com.example.androidnotesproject.utils.noteAdapter.OnNoteClickImpl
import com.example.androidnotesproject.utils.showToastShort
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NotesActivity : AppCompatActivity(), OnNoteClickImpl {

    private val addNoteActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val title = result.data?.getStringExtra("EXTRA_NOTE_TITLE").toString()
                val message = result.data?.getStringExtra("EXTRA_NOTE_MESSAGE").toString()
                addNoteToList(title, message)
                findViewById<RecyclerView>(R.id.notesRecyclerView).run {
                    adapter = NoteAdapter(this@NotesActivity).apply {
                        layoutManager = LinearLayoutManager(this@NotesActivity)
                        submitList(NoteList.list)
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val notesTextViewLogout: TextView = findViewById(R.id.notesTextViewLogout)
        val notesTextViewAddNote: TextView = findViewById(R.id.notesTextViewAddNote)

        //Добавляем данные для теста, пока не реализована загрузка данных из БД\памяти
        addTestNotesToList()

        findViewById<RecyclerView>(R.id.notesRecyclerView).run {
            adapter = NoteAdapter(this@NotesActivity).apply {
                layoutManager = LinearLayoutManager(this@NotesActivity)
                submitList(NoteList.list)
            }
        }

        notesTextViewLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        notesTextViewAddNote.setOnClickListener {
            addNoteActivityResultLauncher.launch(Intent(this, AddNoteActivity::class.java))
        }
    }

    override fun onClick(note: Note) {
        showToastShort(note.title)
    }

    private fun addTestNotesToList() {
        NoteList.list.add(
            Note(
                1,
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(),
                "Title1",
                "Any text blablablablablablablablablabla"
            )
        )
        NoteList.list.add(
            Note(
                2,
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(),
                "Title2",
                "Any text blablablablablablablablablabla"
            )
        )
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {}

}