package com.example.androidnotesproject.ui.onboarding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidnotesproject.databinding.FragmentOnboardingOneBinding
import com.example.androidnotesproject.ui.login.LoginFragment
import com.example.androidnotesproject.navigation.navigator

class OnboardingOneFragment : Fragment() {

    private var binding: FragmentOnboardingOneBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("test OnboardingOneFragment = ", container.toString())
        return FragmentOnboardingOneBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.onboardingOneTextViewSkip?.setOnClickListener{
            navigator().cancelFragment()
            navigator().startFragment(LoginFragment())
        }
    }

}