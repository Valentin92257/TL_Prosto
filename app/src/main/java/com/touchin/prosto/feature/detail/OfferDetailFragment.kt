package com.touchin.prosto.feature.detail

import android.graphics.Color
import androidx.navigation.fragment.navArgs
import com.anadolstudio.core.viewbinding.viewBinding
import com.touchin.prosto.R
import com.touchin.prosto.base.bottom.BaseContentBottom
import com.touchin.prosto.base.fragment.BaseContentFragment
import com.touchin.prosto.databinding.FragmentOfferDetailBinding
import com.touchin.prosto.di.viewmodel.assistedViewModel
import com.touchin.prosto.util.GradientDrawable

@Suppress("TooManyFunctions")
class OfferDetailFragment : BaseContentBottom<OfferDetailState, OfferDetailViewModel, OfferDetailController>(
    R.layout.fragment_offer_detail
) {

    private val binding by viewBinding { FragmentOfferDetailBinding.bind(it) }
    protected val args: OfferDetailFragmentArgs by navArgs()

    override fun createViewModelLazy() = assistedViewModel {
        getSharedComponent()
            .offerDetailViewModelFactory()
            .create(args.offer)
    }

    override fun render(state: OfferDetailState, controller: OfferDetailController) {
        with(binding){
            mainInfo.initView(state.offer)
            headerView.initView(state.offer, {})
            offerName.text = state.offer.companyName
            longDescription.text = state.offer.longDescription
            gradientBackground.background = GradientDrawable(
                firstColor = state.offer.backgroundFirstColor,
                secondColor = state.offer.backgroundSecondColor,
            )
        }
    }
}
