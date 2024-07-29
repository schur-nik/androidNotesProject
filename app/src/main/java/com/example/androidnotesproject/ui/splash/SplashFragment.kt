package com.example.androidnotesproject.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidnotesproject.databinding.FragmentSplashBinding
import com.example.androidnotesproject.ui.onboarding.OnboardingViewPagerFragment
import com.example.androidnotesproject.ui.login.LoginFragment
import com.example.androidnotesproject.navigation.navigator
import com.example.androidnotesproject.repositories.SharedPreferencesRepository

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
            navigator().replaceFragment(OnboardingViewPagerFragment())
        }

        binding?.splashTextViewToLogIn?.setOnClickListener {
            SharedPreferencesRepository.setFirstLaunch()
            navigator().replaceFragment(LoginFragment())
        }
    }

}