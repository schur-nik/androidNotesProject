package com.example.androidnotesproject.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.androidnotesproject.databinding.FragmentOnboardingViewPagerBinding
import com.example.androidnotesproject.utils.adapter.OnboardingAdapter

class OnboardingViewPagerFragment : Fragment() {

    private var binding: FragmentOnboardingViewPagerBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("test OnboardingViewPagerFragment = ", container.toString())
        return FragmentOnboardingViewPagerBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewPager?.run {
            adapter = OnboardingAdapter(childFragmentManager)
        }
    }
}