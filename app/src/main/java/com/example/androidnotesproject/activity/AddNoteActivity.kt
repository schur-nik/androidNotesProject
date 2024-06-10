package com.example.androidnotesproject.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidnotesproject.R
import com.example.androidnotesproject.utils.ValidateResult
import com.example.androidnotesproject.utils.addNoteValidate
import com.example.androidnotesproject.utils.getErrorString

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val addNoteTextViewBack: TextView = findViewById(R.id.addNoteTextViewBack)
        val addNoteButtonMain: Button = findViewById(R.id.addNoteButtonMain)

        addNoteTextViewBack.setOnClickListener {
            finish()
        }

        addNoteButtonMain.setOnClickListener {
            val addNoteEditTextTitle = findViewById<EditText>(R.id.addNoteEditTextTitle)
            val addNoteEditTextMessage = findViewById<EditText>(R.id.addNoteEditTextMessage)

            addNoteValidate(addNoteEditTextTitle.text.toString()).apply {
                when (this) {
                    is ValidateResult.Invalid -> addNoteEditTextTitle.error = getErrorString(errorCode)
                    else -> addNoteEditTextTitle.error = null
                }
            }

            addNoteValidate(addNoteEditTextMessage.text.toString()).apply {
                when (this) {
                    is ValidateResult.Invalid -> addNoteEditTextMessage.error = getErrorString(errorCode)
                    else -> addNoteEditTextMessage.error = null
                }
            }

            if (addNoteEditTextTitle.error.isNullOrBlank() && addNoteEditTextMessage.error.isNullOrBlank()) {
                val resultIntent = Intent()
                resultIntent.putExtra("EXTRA_NOTE_TITLE", addNoteEditTextTitle.text.toString())
                resultIntent.putExtra("EXTRA_NOTE_MESSAGE", addNoteEditTextMessage.text.toString())
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }

}