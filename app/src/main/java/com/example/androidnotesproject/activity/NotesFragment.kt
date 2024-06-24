package com.example.androidnotesproject.activity

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidnotesproject.R
import com.example.androidnotesproject.data.NoteList
import com.example.androidnotesproject.databinding.FragmentNotesBinding
import com.example.androidnotesproject.utils.*
import com.example.androidnotesproject.utils.noteAdapter.NoteAdapter

class NotesFragment : Fragment() {

    private var binding: FragmentNotesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentNotesBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Обработка нажатия кнопки назад
        addBackPressCallback()

        parentFragmentManager.setFragmentResultListener("ADD_NOTE_RESULT_OK", this) { requestKey, bundle ->
            val title = bundle.getString(AddNoteFragment.EXTRA_NOTE_TITLE).toString()
            val message = bundle.getString(AddNoteFragment.EXTRA_NOTE_MESSAGE).toString()
            val date = bundle.getString(AddNoteFragment.EXTRA_NOTE_DATE).toString()
            val scheduled = bundle.getBoolean(AddNoteFragment.EXTRA_NOTE_SCHEDULED)

            if (scheduled)
                addScheduledNoteToList(title, message, date)
            else
                addNoteToList(title, message, date)

            setList()
        }

        setList()

        binding?.notesTextViewLogout?.setOnClickListener {
            navigator().startFragment(LoginFragment())
        }

        binding?.notesTextViewAddNote?.setOnClickListener {
            navigator().addFragment(AddNoteFragment())
        }
    }

    private fun setList() {
        binding?.notesRecyclerView?.run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = NoteAdapter { note -> requireContext().showToastShort(note.title) }
            }
            (adapter as? NoteAdapter)?.submitList(NoteList.getNoteList())
        }
    }

    private var backPressedCallback: OnBackPressedCallback? = null
    private var backStackListener = FragmentManager.OnBackStackChangedListener {
        val currentFragment = parentFragmentManager.findFragmentById(R.id.mainFrame)
        backPressedCallback?.isEnabled = currentFragment is NotesFragment
    }
    private fun addBackPressCallback() {
        backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                AlertDialog.Builder(context).apply {
                    setTitle(getString(R.string.notes_textview_logout))
                    setMessage(getString(R.string.notes_alert_question))
                    setPositiveButton(getString(R.string.notes_alert_answer_yes)) { dialog, which ->
                        parentFragmentManager.removeOnBackStackChangedListener(backStackListener)
                        navigator().startFragment(SplashFragment())
                        navigator().popBackStackAll()
                    }
                    setNegativeButton(getString(R.string.notes_alert_answer_no)) { dialog, which ->
                        dialog.dismiss()
                    }
                    setCancelable(false)
                    create().show()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            backPressedCallback as OnBackPressedCallback
        )
        parentFragmentManager.addOnBackStackChangedListener(backStackListener)
    }

}