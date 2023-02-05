package dev.aashishtathod.noteit.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aashishtathod.noteit.domain.repository.UserRepository
import dev.aashishtathod.noteit.domain.usecase.AuthValidationUseCase
import dev.aashishtathod.noteit.domain.usecase.LoginUseCase

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
	
    @Provides
    fun logInUseCase(
        userRepository: UserRepository
    ): LoginUseCase = LoginUseCase(userRepository)
	
    @Provides
    fun authValidationUseCase(): AuthValidationUseCase = AuthValidationUseCase()
}
