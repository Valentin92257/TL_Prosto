package com.touchin.data.repository.common

import com.touchin.data.repository.offers.toDomain
import com.touchin.domain.repository.common.PreferenceRepository
import com.touchin.domain.repository.offers.OfferDomain

class PreferenceRepositoryImpl(private val preferencesStorage: PreferencesStorage,
) :
    PreferenceRepository {
    override fun getEmail(): String {
        return preferencesStorage.emailDraft
    }

    override fun getSubject(): String {
        return preferencesStorage.subjectDraft
    }

    override fun getBody(): String {
        return preferencesStorage.bodyDraft
    }

}
