package com.example.androidnotesproject.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidnotesproject.databinding.FragmentOnboardingViewPagerBinding
import com.example.androidnotesproject.ui.onboarding.onboardingAdapter.OnboardingAdapter

class OnboardingViewPagerFragment : Fragment() {

    private var binding: FragmentOnboardingViewPagerBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentOnboardingViewPagerBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = OnboardingAdapter(this)
        binding?.run {
            viewPager.adapter = adapter
        }
    }
}