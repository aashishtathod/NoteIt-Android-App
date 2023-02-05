package dev.aashishtathod.noteit.ui.screens.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aashishtathod.noteit.core.presentation.BaseViewModel
import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.domain.exceptions.AuthValidation
import dev.aashishtathod.noteit.domain.usecase.AuthValidationUseCase
import dev.aashishtathod.noteit.domain.usecase.LoginUseCase
import dev.aashishtathod.noteit.domain.usecase.UpdateTokenUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val authValidationUseCase: AuthValidationUseCase,
    private val updateTokenUseCase: UpdateTokenUseCase
) : BaseViewModel<LoginState>(initialState = LoginState()) {
	
    fun setUsername(username: String) {
        setState { state -> state.copy(username = username) }
    }
	
    fun setPassword(password: String) {
        setState { state -> state.copy(password = password) }
    }
	
    fun login() {
        viewModelScope.launch {
            val username = currentState.username
            val password = currentState.password
			
            val validationResponse = authValidationUseCase(username, password, password)
            validationResponse.catch {
                when (it.message?.toInt()) {
                    AuthValidation.INVALID_USERNAME.value -> {
                    }
                    AuthValidation.INVALID_USERNAME.value -> {
                    }
                    AuthValidation.INVALID_USERNAME.value -> {
                    }
                }
            }.collect {
                if (it) {
                    setState { state -> state.copy(isLoading = true) }
					
                    val response = loginUseCase(username, password) as Either<String>
					
                    response.onSuccess { authCredential ->
                    //    sessionManager.saveToken(authCredential.token)
                        setState { state ->
                            state.copy(
                                isLoading = false,
                                isLoggedIn = true,
                                error = null
                            )
                        }
                    }.onFailure { message ->
                        setState { state ->
                            state.copy(
                                isLoading = false,
                                isLoggedIn = false,
                                error = message
                            )
                        }
                    }
                }
            }
        }
    }
}
