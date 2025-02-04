package com.touchin.domain.repository.offers


interface OffersRepository {
    suspend fun getOfferList(): List<OfferDomain>
    fun getFavoriteSet(): HashSet<String>
    fun setFavoriteSet(set: Set<String>)
}
