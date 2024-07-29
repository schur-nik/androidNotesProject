package com.example.androidnotesproject.navigation

import androidx.fragment.app.Fragment

interface Navigation {

    fun replaceFragment(fragment: Fragment, backStack: Boolean = true)

    fun addFragment(fragment: Fragment, backStack: Boolean = true)

    fun cancelFragment()

    fun goBack()

    fun popBackStackAll()
}

fun Fragment.navigator(): Navigation {
    return requireActivity() as Navigation
}