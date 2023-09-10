package com.kmmloginscreen.android.dependencies

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kmmloginscreen.data.repository.ValidationStatusDataRepository
import com.kmmloginscreen.domain.repository.ValidationStatusRepository
import io.kmmtextvalidator.code.textvalidator.TextValidator
import io.kmmtextvalidator.code.textvalidator.TextValidatorImpl

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Reusable
    fun providesValidationStatusRepository(
        textValidator: TextValidator
    ): ValidationStatusRepository = ValidationStatusDataRepository(textValidator)

    @Provides
    @Reusable
    fun providesTextValidator(): TextValidator = TextValidatorImpl()
}
