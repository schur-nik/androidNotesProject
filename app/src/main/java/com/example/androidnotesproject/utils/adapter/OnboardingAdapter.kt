package com.example.androidnotesproject.utils.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidnotesproject.activity.OnboardingOneFragment
import com.example.androidnotesproject.activity.OnboardingTwoFragment

const val FR_NUMBERS = 2
class OnboardingAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = FR_NUMBERS
override fun createFragment(position: Int): Fragment {
    var fragment: Fragment? = null
    when (position) {
        0 -> fragment = OnboardingOneFragment()
        1 -> fragment = OnboardingTwoFragment()
    }
    return fragment ?: Fragment()
}
}