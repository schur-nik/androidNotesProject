package com.example.androidnotesproject.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidnotesproject.R
import com.example.androidnotesproject.data.NoteList
import com.example.androidnotesproject.data.Subscriber
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

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                AlertDialog.Builder(context).apply {
                    setTitle("LOGOUT")
                    setMessage("Are you sure to logout?")
                    setPositiveButton("Yes") { dialog, which ->
                        parentFragmentManager
                            .beginTransaction()
                            .replace(R.id.mainFrame, SplashFragment())
                            .commitNowAllowingStateLoss()
                        requireActivity().supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    }
                    setNegativeButton("No") { dialog, which ->
                        dialog.dismiss()
                    }
                    setCancelable(false)
                    create().show()
                }
            }
        })

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
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.mainFrame, LoginFragment())
                .addToBackStack(null)
                .commit()
        }

        binding?.notesTextViewAddNote?.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .add(R.id.mainFrame, AddNoteFragment())
                .addToBackStack(null)
                .commit()
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

}