package dev.aashishtathod.noteit.domain.exceptions

import dev.aashishtathod.noteit.domain.model.LoginValidation

class AuthValidationException(private val validation: LoginValidation) : Exception()

enum class AuthValidation(val value: Int) {
    INVALID_USERNAME(0),
    INVALID_PASSWORD(1),
    INVALID_CONFIRM_PASSWORD(2)
}
