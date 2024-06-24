package com.example.androidnotesproject.utils.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.androidnotesproject.activity.OnboardingOneFragment
import com.example.androidnotesproject.activity.OnboardingTwoFragment

class OnboardingAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = listOf(OnboardingOneFragment(), OnboardingTwoFragment())

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = fragments[position]

}