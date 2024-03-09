package com.kmmloginscreen.android.dependencies

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kmmloginscreen.android.ui.fragment.FragmentLifecycleBindingCallbacks
import java.util.logging.Logger

@Module
@InstallIn(SingletonComponent::class)
object UiModule {

    @Provides
    fun providesFragmentLifecycleBindingCallbacks(
        logger: Logger
    ) = FragmentLifecycleBindingCallbacks(logger)

    @Provides
    fun providesLogger() = Logger.getGlobal()
}
