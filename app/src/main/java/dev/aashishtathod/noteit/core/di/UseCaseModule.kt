package dev.aashishtathod.noteit.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aashishtathod.noteit.core.data.AppPreferences
import dev.aashishtathod.noteit.domain.repository.AuthRepository
import dev.aashishtathod.noteit.domain.usecase.AuthValidationUseCase
import dev.aashishtathod.noteit.domain.usecase.LoginUseCase
import dev.aashishtathod.noteit.domain.usecase.SaveTokenUseCase

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
	
    @Provides
    fun logInUseCase(
	    authRepository: AuthRepository
    ): LoginUseCase = LoginUseCase(authRepository)
	
    @Provides
    fun authValidationUseCase(): AuthValidationUseCase = AuthValidationUseCase()
    
    @Provides
    fun saveTokenUseCase(
        appPreferences: AppPreferences
    ): SaveTokenUseCase = SaveTokenUseCase(appPreferences)
}
