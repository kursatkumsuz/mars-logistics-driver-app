package com.kursatkumsuz.marslojistiksurucuapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.onboarding.OnBoardingScreen
import com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.signin.SingInScreen
import com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.splah.SplashScreen
import com.kursatkumsuz.marslojistiksurucuapp.util.Screen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(
                onNavigateHomeScreen = { navController.navigate(route = Screen.HomeScreen.route) },
                onNavigateSignInScreen = { navController.navigate(route = Screen.SignInScreen.route) },
                onNavigateOnBoardingScreen = { navController.navigate(route = Screen.OnboardingScreen.route) },
            )
        }
        composable(route = Screen.OnboardingScreen.route) {
            OnBoardingScreen(onNavigateSignInScreen = {
                navController.navigate(route = Screen.SignInScreen.route)
            })
        }
        composable(route = Screen.SignInScreen.route) {
            SingInScreen(onNavigateHomeScreen = {
                navController.navigate(route = Screen.HomeScreen.route)
            })
        }
        composable(route = Screen.HomeScreen.route) {

        }
        composable(route = Screen.DetailScreen.route) {}

    }
}