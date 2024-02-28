package com.touchin.prosto.feature.support

import android.content.Context
import com.touchin.prosto.base.viewmodel.BaseContentViewModel
import com.touchin.prosto.base.viewmodel.navigateUp
import com.touchin.prosto.util.EmailUtil
import com.touchin.prosto.util.sendEmail
import javax.inject.Inject

class SupportViewModel @Inject constructor(
    private val context: Context,
) : BaseContentViewModel<SupportState>(
    initState = SupportState()
), SupportController {

    override fun onSendClicked() {
        if (hasErrors()) return

        sendEmail(
            context = context,
            subject = state.emailSubject, // TODO необходимо изменить в рамках задачи
            email = state.emailText,
            body = state.emailBody // TODO необходимо изменить в рамках задачи
        )
    }

    override fun onEmailChanged(text: String) = updateState { copy(emailText = text, hasEmailError = false) }

    override fun onSubjectChanged(text: String) = updateState { copy(emailSubject = text, hasSubjectError = false) }
    override fun onBodyChanged(text: String) = updateState { copy(emailBody = text, hasBodyError = false) }
    private fun hasErrors(): Boolean {
        val hasEmailError = !EmailUtil.EMAIL_PATTERN_REGEX.matches(state.emailText)
        updateState { copy(hasEmailError = hasEmailError) }
        val hasSubjectError = state.emailSubject.contains(Regex("""[A-Za-z]"""))
        updateState { copy(hasSubjectError = hasSubjectError) }
        val hasBodyError = state.emailBody.contains(Regex("""[A-Za-z]"""))
        updateState { copy(hasBodyError = hasBodyError) }
        return listOf(hasEmailError, hasSubjectError, hasBodyError).any { hasError -> hasError }
    }

    override fun onBackClicked() = _navigationEvent.navigateUp()

}
