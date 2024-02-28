package com.touchin.prosto.feature.list

import android.content.Context
import androidx.core.os.bundleOf
import androidx.lifecycle.viewModelScope
import com.anadolstudio.core.viewmodel.lce.lceFlow
import com.anadolstudio.core.viewmodel.lce.mapLceContent
import com.anadolstudio.core.viewmodel.lce.onEachContent
import com.anadolstudio.core.viewmodel.lce.onEachError
import com.touchin.domain.repository.offers.OffersRepository
import com.touchin.prosto.R
import com.touchin.prosto.base.viewmodel.BaseContentViewModel
import com.touchin.prosto.base.viewmodel.navigateTo
import com.touchin.prosto.base.viewmodel.navigateUp
import com.touchin.prosto.feature.model.OfferUi
import com.touchin.prosto.feature.model.toUi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class OfferListViewModel @Inject constructor(
    private val context: Context,
    private val offersRepository: OffersRepository
) : BaseContentViewModel<OfferListState>(
    OfferListState()
), OfferListController {

    init {

        loadOffers()
    }

    private fun loadOffers() {
        lceFlow { emit(offersRepository.getOfferList()) }
            .mapLceContent { offers -> offers.map { it.toUi(isFavorite = offersRepository.getFavoriteSet().contains(it.id)) } }
            .onEach { updateState { copy(loadingState = it) } }
            .onEachContent { offers ->updateState { copy(offersList = offers,isFavoritesAvailable =offersRepository.getFavoriteSet().isNotEmpty()) } }
            .onEachError { showError(it) }
            .launchIn(viewModelScope)
    }

    override fun onBackClicked() = _navigationEvent.navigateUp()

    override fun onOfferClicked(offerUi: OfferUi) = _navigationEvent.navigateTo(
        id = R.id.action_offerListFragment_to_offerBottom,
        args = bundleOf(context.getString(R.string.navigation_offer) to offerUi)
    )


    override fun onFavoriteChecked(offerUi: OfferUi) {
        val favoritesSet: HashSet<String> = offersRepository.getFavoriteSet()
        if (!favoritesSet.contains(offerUi.id))
            favoritesSet.add(offerUi.id)
        else
            favoritesSet.remove(offerUi.id)

        offersRepository.setFavoriteSet(favoritesSet)

        val newOffersList = state.offersList.toMutableList().map {
            if (offerUi.id == it.id) {
                it.copy(isFavorite = !offerUi.isFavorite)
            } else {
                it
            }
        }
        val FavoritesNotEmpty = offersRepository.getFavoriteSet().isNotEmpty()
        updateState {
            copy(
                offersList = newOffersList, isFavoritesAvailable = FavoritesNotEmpty,
                isFavoriteFilter = if (FavoritesNotEmpty)
                    isFavoriteFilter else !isFavoriteFilter
            )
        }
    }

    override fun onFavoriteFilterClicked() {
        updateState { copy(isFavoriteFilter = !isFavoriteFilter,isFavoritesAvailable =offersRepository.getFavoriteSet().isNotEmpty())}
    }
}
