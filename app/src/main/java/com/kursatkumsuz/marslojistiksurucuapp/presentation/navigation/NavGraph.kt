package com.kursatkumsuz.marslojistiksurucuapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.detail.DetailScreen
import com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.home.HomeScreen
import com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.onboarding.OnBoardingScreen
import com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.signin.SingInScreen
import com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.splash.SplashScreen
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
            HomeScreen(onNavigateToDetailScreen = { orderNo ->
                navController.navigate("${Screen.DetailScreen.route}/${orderNo}")

            })
        }
        composable(route = "${Screen.DetailScreen.route}/{orderNo}", arguments = listOf(
            navArgument("orderNo") { type = NavType.LongType }
        )) {
            DetailScreen()
        }

    }
}