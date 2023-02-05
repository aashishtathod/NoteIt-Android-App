package dev.aashishtathod.noteit.domain.usecase

import dev.aashishtathod.noteit.domain.exceptions.AuthValidation
import dev.aashishtathod.noteit.domain.exceptions.AuthValidationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthValidationUseCase @Inject constructor() {
	
    @Throws(AuthValidationException::class)
    operator fun invoke(
        username: String,
        password: String,
        confirmedPassword: String
    ): Flow<Boolean> = flow {
        if (!isValidUsername(username)) {
            throw AuthValidationException(AuthValidation.INVALID_USERNAME.value)
        }
		
        if (!isValidPassword(password)) {
            throw AuthValidationException(AuthValidation.INVALID_PASSWORD.value)
        }
		
        if (!isPasswordAndConfirmPasswordSame(password, confirmedPassword)) {
            throw AuthValidationException(AuthValidation.INVALID_CONFIRM_PASSWORD.value)
        }
		
        emit(true)
    }
	
    private fun isValidUsername(username: String): Boolean = username.trim().length in (4..30)
    private fun isValidPassword(password: String): Boolean = password.trim().length in (8..50)
	
    private fun isPasswordAndConfirmPasswordSame(
        password: String,
        confirmedPassword: String
    ): Boolean = password.trim() == confirmedPassword.trim()
}
