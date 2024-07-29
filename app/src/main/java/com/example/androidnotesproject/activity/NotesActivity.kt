package com.example.androidnotesproject.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidnotesproject.data.NoteList
import com.example.androidnotesproject.databinding.ActivityNotesBinding
import com.example.androidnotesproject.utils.*
import com.example.androidnotesproject.utils.noteAdapter.NoteAdapter

class NotesActivity : AppCompatActivity() {

    private var binding: ActivityNotesBinding? = null

    private val addNoteActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val title = result.data?.getStringExtra(AddNoteActivity.EXTRA_NOTE_TITLE).toString()
                val message = result.data?.getStringExtra(AddNoteActivity.EXTRA_NOTE_MESSAGE).toString()
                val date = result.data?.getStringExtra(AddNoteActivity.EXTRA_NOTE_DATE).toString()
                val scheduled = result.data?.getBooleanExtra(AddNoteActivity.EXTRA_NOTE_SCHEDULED, false)

                if (scheduled!!) {
                    addScheduledNoteToList(title, message, date)
                } else {
                    addNoteToList(title, message, date)
                }

                binding?.notesRecyclerView?.run {
                    adapter = NoteAdapter { note -> showToastShort(note.title) }.apply {
                        layoutManager = LinearLayoutManager(this@NotesActivity)
                        submitList(NoteList.noteList)
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding?.notesRecyclerView?.run {
            adapter = NoteAdapter { note -> showToastShort(note.title) }.apply {
                layoutManager = LinearLayoutManager(this@NotesActivity)
                submitList(NoteList.noteList)
            }
        }

        binding?.notesTextViewLogout?.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding?.notesTextViewAddNote?.setOnClickListener {
            addNoteActivityResultLauncher.launch(Intent(this, AddNoteActivity::class.java))
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
    }

}