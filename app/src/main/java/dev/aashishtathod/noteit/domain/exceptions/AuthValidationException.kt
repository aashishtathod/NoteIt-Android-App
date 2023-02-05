package dev.aashishtathod.noteit.domain.exceptions

class AuthValidationException(private val validationType: Int) :
    Exception(validationType.toString())

enum class AuthValidation(val value: Int) {
    INVALID_USERNAME(0),
    INVALID_PASSWORD(1),
    INVALID_CONFIRM_PASSWORD(2)
}
