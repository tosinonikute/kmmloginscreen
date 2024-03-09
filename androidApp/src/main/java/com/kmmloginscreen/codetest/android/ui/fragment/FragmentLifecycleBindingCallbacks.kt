package com.kmmloginscreen.android.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
import java.util.logging.Level
import java.util.logging.Logger

class FragmentLifecycleBindingCallbacks(private val logger: Logger) : FragmentLifecycleCallbacks() {

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        log(f, "onFragmentCreated")
        super.onFragmentCreated(fm, f, savedInstanceState)
    }

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentStarted")
        super.onFragmentStarted(fm, f)
    }

    override fun onFragmentResumed(fragmentManager: FragmentManager, fragment: Fragment) {
        log(fragment, "onFragmentResumed")
        super.onFragmentResumed(fragmentManager, fragment)
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentStopped")
        super.onFragmentStopped(fm, f)
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentDestroyed")
        super.onFragmentDestroyed(fm, f)
    }

    private fun log(fragment: Fragment, lifecycleCallbackName: String) {
        val fragmentName = fragment.toString().substringBefore('{')
        logger.log(Level.INFO, "Fragment: $fragmentName, Lifecycle: $lifecycleCallbackName")
    }
}
