package com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.ui.res.stringResource
import com.kursatkumsuz.marslojistiksurucuapp.R

sealed class OnBoardingPage(
    val title: Int,
    val description: Int,
    @DrawableRes val image: Int
) {
    data object First : OnBoardingPage(
        title = R.string.onboarding_first_title,
        description = R.string.onboarding_first_description,
        image = R.drawable.onboarding_image_first
    )

    data object Second : OnBoardingPage(
        title = R.string.onboarding_second_title,
        description = R.string.onboarding_second_description,
        image = R.drawable.onboarding_image_second
    )

    data object Third : OnBoardingPage(
        title = R.string.onboarding_third_title,
        description = R.string.onboarding_third_description,
        image = R.drawable.onboarding_image_third
    )
}