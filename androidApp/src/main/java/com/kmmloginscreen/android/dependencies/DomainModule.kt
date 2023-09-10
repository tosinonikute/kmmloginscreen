package com.kmmloginscreen.android.dependencies

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kmmloginscreen.android.presentation.base.DebugErrorLogger
import com.kmmloginscreen.android.presentation.base.UseCaseExecutorProvider
import com.kmmloginscreen.domain.base.CoroutineContextProvider
import com.kmmloginscreen.domain.base.DispatchersCoroutineContextProvider
import com.kmmloginscreen.domain.base.ErrorLogger
import com.kmmloginscreen.domain.base.UseCaseExecutor
import com.kmmloginscreen.domain.repository.ValidationStatusRepository
import com.kmmloginscreen.domain.usecase.ValidateEmailUsecase
import com.kmmloginscreen.domain.usecase.ValidateEmailUsecaseImpl
import com.kmmloginscreen.domain.usecase.ValidatePasswordUsecase
import com.kmmloginscreen.domain.usecase.ValidatePasswordUsecaseImpl

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Reusable
    fun provideCoroutineContextProvider(): CoroutineContextProvider =
        DispatchersCoroutineContextProvider()

    @Provides
    @Reusable
    fun providesUseCaseExecutorProvider(errorLogger: ErrorLogger): UseCaseExecutorProvider =
        { coroutineScope -> UseCaseExecutor(coroutineScope, errorLogger) }

    @Provides
    @Reusable
    fun providesErrorLogger(): ErrorLogger = DebugErrorLogger()

    @Provides
    fun providesValidateEmailUsecase(
        coroutineContextProvider: CoroutineContextProvider,
        validationStatusRepository: ValidationStatusRepository
    ): ValidateEmailUsecase = ValidateEmailUsecaseImpl(
        coroutineContextProvider,
        validationStatusRepository
    )

    @Provides
    fun providesValidatePasswordUsecase(
        coroutineContextProvider: CoroutineContextProvider,
        validationStatusRepository: ValidationStatusRepository
    ): ValidatePasswordUsecase = ValidatePasswordUsecaseImpl(
        coroutineContextProvider,
        validationStatusRepository
    )
}
