package com.example.androidnotesproject.activity

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.androidnotesproject.databinding.FragmentAddNoteBinding
import com.example.androidnotesproject.utils.ValidateResult
import com.example.androidnotesproject.utils.addNoteValidate
import com.example.androidnotesproject.utils.getErrorString
import com.example.androidnotesproject.utils.toSimpleText
import java.time.LocalDate
import java.util.Calendar

class AddNoteFragment : Fragment() {

    private var binding: FragmentAddNoteBinding? = null

    companion object {
        const val EXTRA_NOTE_TITLE = "noteTitle"
        const val EXTRA_NOTE_MESSAGE = "noteMessage"
        const val EXTRA_NOTE_DATE = "noteDate"
        const val EXTRA_NOTE_SCHEDULED = "noteScheduled"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAddNoteBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.addNoteTextViewBack?.setOnClickListener {
            parentFragmentManager.setFragmentResult("ADD_NOTE_RESULT_BACK", Bundle())
            parentFragmentManager.popBackStack()
        }

        binding?.run {
            addNoteCheckBoxScheduled.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    addNoteEditTextDate.visibility = View.VISIBLE
                } else {
                    addNoteEditTextDate.visibility = View.INVISIBLE
                }
            }
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
                        is ValidateResult.Invalid -> addNoteEditTextTitle.error = requireContext().getErrorString(errorCode)
                        else -> addNoteEditTextTitle.error = null
                    }
                }

                addNoteValidate(addNoteEditTextMessage.text.toString()).apply {
                    when (this) {
                        is ValidateResult.Invalid -> addNoteEditTextMessage.error = requireContext().getErrorString(errorCode)
                        else -> addNoteEditTextMessage.error = null
                    }
                }

                addNoteValidate(addNoteEditTextDate.text.toString(), "DATE").apply {
                    when (this) {
                        is ValidateResult.Invalid -> addNoteEditTextDate.error = requireContext().getErrorString(errorCode)
                        else -> addNoteEditTextDate.error = null
                    }
                }

                if (addNoteEditTextTitle.error.isNullOrBlank() && addNoteEditTextMessage.error.isNullOrBlank() && addNoteEditTextDate.error.isNullOrBlank()) {
                    val result = Bundle().apply {
                        putString(
                            EXTRA_NOTE_TITLE,
                            addNoteEditTextTitle.text.toString()
                        )
                        putString(
                            EXTRA_NOTE_MESSAGE,
                            addNoteEditTextMessage.text.toString()
                        )
                        putString(
                            EXTRA_NOTE_DATE,
                            if (addNoteCheckBoxScheduled.isChecked) addNoteEditTextDate.text.toString() else LocalDate.now()
                                .toSimpleText()
                        )
                        putBoolean(
                            EXTRA_NOTE_SCHEDULED,
                            addNoteCheckBoxScheduled.isChecked
                        )
                    }
                    parentFragmentManager.setFragmentResult("ADD_NOTE_RESULT_OK", result)
                    parentFragmentManager.popBackStack()

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