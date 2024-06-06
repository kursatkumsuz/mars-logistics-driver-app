package com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.onboarding.viewmodel.OnBoardingViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    onNavigateSignInScreen: () -> Unit
) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )
    val viewModel: OnBoardingViewModel = hiltViewModel()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        pages.size
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            val pageOffset = (pagerState.currentPage - position) + pagerState.currentPageOffsetFraction

            OnBoardingContent(
                onBoardingPage = pages[position],
                pageOffset = pageOffset
            )
        }
        Spacer(modifier = Modifier.height(50.dp))

        Spacer(modifier = Modifier.height(20.dp))
        StartButton(pagerState = pagerState, onStartClick = {
            viewModel.saveOnBoardingState()
            onNavigateSignInScreen()
        })
    }
}