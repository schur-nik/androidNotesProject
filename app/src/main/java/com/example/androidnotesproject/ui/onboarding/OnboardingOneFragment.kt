package com.example.androidnotesproject.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidnotesproject.databinding.FragmentOnboardingOneBinding
import com.example.androidnotesproject.ui.login.LoginFragment
import com.example.androidnotesproject.navigation.navigator
import com.example.androidnotesproject.repositories.SharedPreferencesRepository

class OnboardingOneFragment : Fragment() {

    private var binding: FragmentOnboardingOneBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentOnboardingOneBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.onboardingOneTextViewSkip?.setOnClickListener{
            SharedPreferencesRepository.setFirstLaunch()
            navigator().cancelFragment()
            navigator().replaceFragment(LoginFragment())
        }
    }

}