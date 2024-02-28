package com.touchin.data.repository.common

import androidx.appcompat.app.AppCompatDelegate
import com.ironz.binaryprefs.Preferences
import com.ironz.binaryprefs.PreferencesEditor

@Suppress("TooManyFunctions")
class PreferencesStorage(private val preferences: Preferences) {

    private companion object {
        const val NIGHT_MODE = "NIGHT_MODE"
        const val EMAIL_DRAFT = "EMAIL_DRAFT"
        const val SUBJECT_DRAFT = "SUBJECT_DRAFT"
        const val BODY_DRAFT = "BODY_DRAFT"
        const val FAVORITES = "FAVORITES"
    }

    var nightMode: Int
        set(value) = preferences.modify { putInt(NIGHT_MODE, value) }
        get() = preferences.getInt(NIGHT_MODE, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

    var favorites: Set<String>
        set(fav) = preferences.modify { putStringSet(FAVORITES,fav)}
        get() = preferences.getStringSet(FAVORITES, emptySet()).orEmpty()

    var emailDraft: String
        set(value) = preferences.modify { putString(EMAIL_DRAFT, value) }
        get() = preferences.getString(EMAIL_DRAFT, "").orEmpty()

    var subjectDraft: String
        set(value) = preferences.modify { putString(SUBJECT_DRAFT, value) }
        get() = preferences.getString(SUBJECT_DRAFT, "").orEmpty()

    var bodyDraft: String
        set(value) = preferences.modify { putString(BODY_DRAFT, value) }
        get() = preferences.getString(BODY_DRAFT, "").orEmpty()

    private inline fun Preferences.modify(
        commit: Boolean = false,
        action: PreferencesEditor.() -> Unit
    ) {
        with(edit()) {
            action(this)
            if (commit) commit() else apply()
        }
    }
}
