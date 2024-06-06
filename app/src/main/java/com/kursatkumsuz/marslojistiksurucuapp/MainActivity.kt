package com.kursatkumsuz.marslojistiksurucuapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.kursatkumsuz.marslojistiksurucuapp.presentation.navigation.NavGraph
import com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.onboarding.OnBoardingScreen
import com.kursatkumsuz.marslojistiksurucuapp.ui.theme.MarsLojistikSürücüUygulamasıTheme
import com.kursatkumsuz.marslojistiksurucuapp.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarsLojistikSürücüUygulamasıTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarsLojistikSürücüUygulamasıTheme {
    }
}