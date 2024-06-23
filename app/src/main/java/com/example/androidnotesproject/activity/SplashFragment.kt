package com.example.androidnotesproject.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidnotesproject.R
import com.example.androidnotesproject.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var binding: FragmentSplashBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSplashBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.splashButtonMain?.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.mainFrame, SignUpFragment())
                .addToBackStack(null)
                .commit()
        }

        binding?.splashTextViewToLogIn?.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.mainFrame, LoginFragment())
                .addToBackStack(null)
                .commit()
        }
    }

}