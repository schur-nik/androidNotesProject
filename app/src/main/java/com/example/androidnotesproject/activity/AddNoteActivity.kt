package com.example.androidnotesproject.activity

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidnotesproject.databinding.ActivityAddNoteBinding
import com.example.androidnotesproject.utils.ValidateResult
import com.example.androidnotesproject.utils.addNoteValidate
import com.example.androidnotesproject.utils.getErrorString
import com.example.androidnotesproject.utils.toSimpleText
import java.time.LocalDate
import java.util.Calendar

class AddNoteActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NOTE_TITLE = "noteTitle"
        const val EXTRA_NOTE_MESSAGE = "noteMessage"
        const val EXTRA_NOTE_DATE = "noteDate"
    }

    private var binding: ActivityAddNoteBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding?.addNoteTextViewBack?.setOnClickListener {
            finish()
        }

        binding?.run{
            addNoteEditTextDate.editableText.append(LocalDate.now().toSimpleText())
            addNoteEditTextDate.setOnClickListener {
                showDatePicker(addNoteEditTextDate)
            }
        }

        binding?.run {
            addNoteButtonMain.setOnClickListener {
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

                addNoteValidate(addNoteEditTextDate.text.toString()).apply {
                    when (this) {
                        is ValidateResult.Invalid -> addNoteEditTextDate.error = getErrorString(errorCode)
                        else -> addNoteEditTextDate.error = null
                    }
                }

                if (addNoteEditTextTitle.error.isNullOrBlank() && addNoteEditTextMessage.error.isNullOrBlank() && addNoteEditTextDate.error.isNullOrBlank()) {
                    val resultIntent = Intent()
                    resultIntent.putExtra(EXTRA_NOTE_TITLE, addNoteEditTextTitle.text.toString())
                    resultIntent.putExtra(EXTRA_NOTE_MESSAGE, addNoteEditTextMessage.text.toString())
                    resultIntent.putExtra(EXTRA_NOTE_DATE, addNoteEditTextDate.text.toString())
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }
            }
        }
    }

    private fun showDatePicker(textView: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(textView.context, { _, selectedYear, selectedMonth, dayOfMonth ->
            textView.text = LocalDate.of(selectedYear, selectedMonth + 1, dayOfMonth).toSimpleText()
        }, year, month, day)

        dpd.show()
    }

}