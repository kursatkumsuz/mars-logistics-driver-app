package com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.kursatkumsuz.marslojistiksurucuapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateHomeScreen: () -> Unit,
    onNavigateSignInScreen: () -> Unit,
    onNavigateOnBoardingScreen: () -> Unit
) {

    val viewModel: SplashViewModel = hiltViewModel()
    val onBoardingState = viewModel.onBoardingState
    val signedInState = viewModel.signedInState

    //Screen Content
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Splash Logo")
    }

    LaunchedEffect(key1 = onBoardingState) {
        delay(1000)
        if (onBoardingState && signedInState) {
            onNavigateHomeScreen()
        } else if (onBoardingState) {
            onNavigateSignInScreen()
        } else {
            onNavigateOnBoardingScreen()
        }
    }
}