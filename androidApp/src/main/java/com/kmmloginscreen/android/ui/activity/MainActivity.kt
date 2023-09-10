package com.kmmloginscreen.android.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import com.kmmloginscreen.android.R
import com.kmmloginscreen.android.ui.fragment.FragmentLifecycleBindingCallbacks
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentLifecycleBindingCallbacks: FragmentLifecycleBindingCallbacks

    private val navHostFragment: NavHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindFragmentLifecycle()
    }

    private fun bindFragmentLifecycle() {
        navHostFragment.childFragmentManager.registerFragmentLifecycleCallbacks(
            fragmentLifecycleBindingCallbacks,
            false
        )
    }
}
