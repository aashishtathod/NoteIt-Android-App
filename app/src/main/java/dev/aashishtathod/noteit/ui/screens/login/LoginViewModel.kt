package dev.aashishtathod.noteit.ui.screens.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aashishtathod.noteit.core.presentation.BaseViewModel
import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.domain.exceptions.AuthValidation
import dev.aashishtathod.noteit.domain.usecase.AuthValidationUseCase
import dev.aashishtathod.noteit.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val authValidationUseCase: AuthValidationUseCase
//    private val updateTokenUseCase: UpdateTokenUseCase
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
			
            authValidationUseCase(username, password, password).catch {
                when (it.message?.toInt()) {
                    AuthValidation.INVALID_USERNAME.value -> {
                        setState { state -> state.copy(isValidUsername = false) }
                    }
                    AuthValidation.INVALID_USERNAME.value -> {
                        setState { state -> state.copy(isValidPassword = false) }
                    }
                    AuthValidation.INVALID_USERNAME.value -> {
                        setState { state -> state.copy(isValidPassword = false) }
                    }
                }
            }.collect {
                if (it) {
                    setState { state ->
                        state.copy(
                            isLoading = true,
                            isValidUsername = true,
                            isValidPassword = true
                        )
                    }
					
                    loginUseCase(username, password).collect {
                        when (it) {
                            is Either.Success -> {
                                //    sessionManager.saveToken(authCredential.token)
                                setState { state ->
                                    state.copy(isLoading = false, isLoggedIn = true, error = null)
                                }
                            }
                            is Either.Error -> {
                                setState { state ->
                                    state.copy(
                                        isLoading = false,
                                        isLoggedIn = false,
                                        error = it.message
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
