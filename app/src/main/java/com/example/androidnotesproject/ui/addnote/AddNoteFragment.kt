package com.example.androidnotesproject.ui.addnote

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidnotesproject.databinding.FragmentAddNoteBinding
import com.example.androidnotesproject.extensions.getErrorString
import com.example.androidnotesproject.extensions.toSimpleText
import com.example.androidnotesproject.utils.ValidateResult
import java.time.LocalDate
import java.util.Calendar

class AddNoteFragment : Fragment() {

    private var binding: FragmentAddNoteBinding? = null

    private val viewModel: AddNoteViewModel by viewModels()

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

        binding?.addNoteEditTextDate?.run {
            editableText.append(LocalDate.now().toSimpleText())
            setOnClickListener {
                showDatePicker(this)
            }
        }

        binding?.run {
            addNoteButtonMain.setOnClickListener {

                viewModel.addNoteValidate(addNoteEditTextTitle.text.toString()).apply {
                    when (this) {
                        is ValidateResult.Invalid -> addNoteEditTextTitle.error =
                            requireContext().getErrorString(errorCode)

                        else -> addNoteEditTextTitle.error = null
                    }
                }

                viewModel.addNoteValidate(addNoteEditTextMessage.text.toString()).apply {
                    when (this) {
                        is ValidateResult.Invalid -> addNoteEditTextMessage.error =
                            requireContext().getErrorString(errorCode)

                        else -> addNoteEditTextMessage.error = null
                    }
                }

                viewModel.addNoteValidate(addNoteEditTextDate.text.toString()).apply {
                    when (this) {
                        is ValidateResult.Invalid -> addNoteEditTextDate.error =
                            requireContext().getErrorString(errorCode)

                        else -> addNoteEditTextDate.error = null
                    }
                }

                //TODO Как это вынести в модель?
                if (addNoteEditTextTitle.error.isNullOrBlank() && addNoteEditTextMessage.error.isNullOrBlank() && addNoteEditTextDate.error.isNullOrBlank()) {
                    val scheduled = addNoteCheckBoxScheduled.isChecked

                    viewModel.addNote(
                        addNoteEditTextTitle,
                        addNoteEditTextMessage,
                        addNoteEditTextDate,
                        scheduled
                    )

                    parentFragmentManager.popBackStack()
                }
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
