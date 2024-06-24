package com.example.androidnotesproject.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidnotesproject.R
import com.example.androidnotesproject.databinding.FragmentSplashBinding
import com.example.androidnotesproject.fragment.OnboardingViewPagerFragment

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
            navigator().startFragment(OnboardingViewPagerFragment())
        }

        binding?.splashTextViewToLogIn?.setOnClickListener {
            navigator().startFragment(LoginFragment())
        }
    }

}