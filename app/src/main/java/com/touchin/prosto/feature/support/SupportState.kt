package com.touchin.prosto.feature.support

data class SupportState(
    val emailText: String = "",
    val emailSubject: String = "",
    val emailBody: String = "",
    val hasEmailError: Boolean = false,
    val hasSubjectError: Boolean = false,
    val hasBodyError: Boolean = false
) {
    val sendButtonEnable: Boolean
        get() = listOf(
            emailText.isBlank(),
            emailSubject.isBlank(),
            emailBody.isBlank(),
            hasEmailError,
            hasSubjectError,
            hasBodyError
        ).all { hasError -> !hasError }
}

