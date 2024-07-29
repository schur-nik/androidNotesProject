package com.example.androidnotesproject.navigation

import androidx.fragment.app.Fragment

interface Navigation {

    fun startFragment(fragment: Fragment, backStackName: String? = null)

    fun addFragment(fragment: Fragment, backStackName: String? = null)

    fun cancelFragment()

    fun goBack()

    fun popBackStackAll()
}

fun Fragment.navigator(): Navigation {
    return requireActivity() as Navigation
}