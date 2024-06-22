package com.example.androidnotesproject.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidnotesproject.data.Note
import com.example.androidnotesproject.data.NoteList
import com.example.androidnotesproject.databinding.ActivityNotesBinding
import com.example.androidnotesproject.utils.addNoteToList
import com.example.androidnotesproject.utils.noteAdapter.NoteAdapter
import com.example.androidnotesproject.utils.showToastShort
import java.time.LocalDate

class NotesActivity : AppCompatActivity() {

    private var binding: ActivityNotesBinding? = null

    private val addNoteActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val title = result.data?.getStringExtra(AddNoteActivity.EXTRA_NOTE_TITLE).toString()
                val message = result.data?.getStringExtra(AddNoteActivity.EXTRA_NOTE_MESSAGE).toString()
                val date = result.data?.getStringExtra(AddNoteActivity.EXTRA_NOTE_DATE).toString()

                addNoteToList(title, message, date)

                binding?.notesRecyclerView?.run {
                    adapter = NoteAdapter { note -> showToastShort(note.title) }.apply {
                        layoutManager = LinearLayoutManager(this@NotesActivity)
                        submitList(NoteList.list)
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        //Добавляем данные для теста, пока не реализована загрузка данных из БД\памяти
        addTestNotesToList()

        binding?.notesRecyclerView?.run {
            adapter = NoteAdapter { note -> showToastShort(note.title) }.apply {
                layoutManager = LinearLayoutManager(this@NotesActivity)
                submitList(NoteList.list)
            }
        }

        binding?.notesTextViewLogout?.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding?.notesTextViewAddNote?.setOnClickListener {
            addNoteActivityResultLauncher.launch(Intent(this, AddNoteActivity::class.java))
        }
    }

    private fun addTestNotesToList() {
        NoteList.list.add(
            Note(
                1,
                LocalDate.now(),
                "Title1",
                "Any text blablablablablablablablablabla"
            )
        )
        NoteList.list.add(
            Note(
                2,
                LocalDate.now(),
                "Title2",
                "Any text blablablablablablablablablabla"
            )
        )
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
    }

}