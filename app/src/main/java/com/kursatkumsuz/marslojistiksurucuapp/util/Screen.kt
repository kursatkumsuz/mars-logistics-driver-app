package com.kursatkumsuz.marslojistiksurucuapp.util

sealed class Screen (val route : String) {
    data object SplashScreen : Screen(route = "splash_screen")
    data object OnboardingScreen : Screen(route = "onboarding_screen")
    data object SignInScreen : Screen(route = "sign_in_screen")
    data object HomeScreen : Screen(route = "home_screen")
    data object DetailScreen : Screen(route = "detail_screen")
}