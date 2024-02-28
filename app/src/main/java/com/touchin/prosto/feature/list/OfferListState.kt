package com.touchin.prosto.feature.list

import com.anadolstudio.core.viewmodel.lce.LceState
import com.touchin.prosto.feature.model.OfferUi

data class OfferListState(
    val loadingState: LceState = LceState.Loading,
    val offersList: List<OfferUi> = emptyList(),
    val isFavoritesAvailable:Boolean = false,
    val isFavoriteFilter:Boolean = false
){
    val favoritesList:List<OfferUi> get() = offersList.filter {
        it.isFavorite && isFavoriteFilter || !isFavoriteFilter
    }
}

