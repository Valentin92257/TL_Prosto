package com.touchin.data.repository.offers

import com.touchin.data.api.OffersApi
import com.touchin.data.repository.common.PreferencesStorage
import com.touchin.domain.repository.offers.OfferDomain
import com.touchin.domain.repository.offers.OffersRepository

class OffersRepositoryImpl(private val api: OffersApi, private val preferences: PreferencesStorage) : OffersRepository {

    override suspend fun getOfferList(): List<OfferDomain> = api.getOfferList().map { it.toDomain() }
    override fun getFavoriteSet(): HashSet<String> {
        return preferences.favorites.toHashSet()
    }

    override fun setFavoriteSet(set: Set<String>) {
        preferences.favorites = set
    }
}
