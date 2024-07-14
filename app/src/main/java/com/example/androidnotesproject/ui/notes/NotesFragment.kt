package com.example.androidnotesproject.ui.notes

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidnotesproject.R
import com.example.androidnotesproject.data.NoteItem
import com.example.androidnotesproject.databinding.FragmentNotesBinding
import com.example.androidnotesproject.extensions.showToastShort
import com.example.androidnotesproject.ui.splash.SplashFragment
import com.example.androidnotesproject.ui.addnote.AddNoteFragment
import com.example.androidnotesproject.ui.login.LoginFragment
import com.example.androidnotesproject.navigation.navigator
import com.example.androidnotesproject.ui.notes.noteAdapter.NoteAdapter

class NotesFragment : Fragment() {

    private var binding: FragmentNotesBinding? = null

    private val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentNotesBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.list.observe(viewLifecycleOwner) {
            setList(it)
        }
        viewModel.getNoteList()

        binding?.notesTextViewLogout?.setOnClickListener {
            parentFragmentManager.removeOnBackStackChangedListener(backStackListener)
//            navigator().popBackStackAll()
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, LoginFragment())
                .commit()
        }

        binding?.notesTextViewAddNote?.setOnClickListener {
            navigator().addFragment(AddNoteFragment())
        }

        //Обработка нажатия кнопки назад
        addBackPressCallback()
    }

    private fun setList(listNote : List<NoteItem>) {
        binding?.notesRecyclerView?.run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = NoteAdapter { note -> requireContext().showToastShort(note.title) }
            }
            (adapter as? NoteAdapter)?.submitList(listNote)
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
                    setPositiveButton(getString(R.string.notes_alert_answer_yes)) { _, _ ->
                        parentFragmentManager.removeOnBackStackChangedListener(backStackListener)
                        navigator().startFragment(SplashFragment())
                        navigator().popBackStackAll()
                    }
                    setNegativeButton(getString(R.string.notes_alert_answer_no)) { dialog, _ ->
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