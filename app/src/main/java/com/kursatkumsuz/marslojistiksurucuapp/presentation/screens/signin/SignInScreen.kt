package com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kursatkumsuz.marslojistiksurucuapp.R
import com.kursatkumsuz.marslojistiksurucuapp.presentation.components.signin.AuthButton
import com.kursatkumsuz.marslojistiksurucuapp.presentation.components.signin.InputText
import com.kursatkumsuz.marslojistiksurucuapp.presentation.components.signin.PasswordText
import com.kursatkumsuz.messagebar.BarType
import com.kursatkumsuz.messagebar.MessageBarContent
import com.kursatkumsuz.messagebar.rememberAnimatedBarState
import kotlinx.coroutines.delay

@Composable
fun SingInScreen(onNavigateHomeScreen: () -> Unit) {

    val viewModel: SignInViewModel = hiltViewModel()

    val state = viewModel.state

    val barState = rememberAnimatedBarState()

    MessageBarContent(barState = barState) {
        SignInContent(
            isLoading = state.isLoading,
            isSignedIn = state.isAuthenticated,
            onNavigateHomeScreen = onNavigateHomeScreen,
            onSignInClick = { mail, password ->
                viewModel.signInWithEmailAndPassword(
                    email = mail,
                    password = password,
                    onError = { msg ->
                        barState.AnimatedMessageBar(
                            message = msg,
                            type = BarType.ERROR
                        )
                    },
                    onSuccess = {
                        barState.AnimatedMessageBar(
                            message = "Successfully Signed In!",
                            type = BarType.SUCCESS
                        )
                    }
                )
            })
    }
}

@Composable
fun SignInContent(
    isLoading: Boolean,
    isSignedIn: Boolean,
    onNavigateHomeScreen: () -> Unit,
    onSignInClick: (String, String) -> Unit,
) {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LaunchedEffect(key1 = isSignedIn) {
            if (isSignedIn) {
                delay(2000)
                onNavigateHomeScreen()
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Icon Image")
        Spacer(modifier = Modifier.height(60.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.text_sign_in),
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(30.dp))
            InputText(labelText = "E-mail", onInput = { it -> email = it })
            Spacer(modifier = Modifier.height(15.dp))
            PasswordText(onInput = { it -> password = it })
            Spacer(modifier = Modifier.height(30.dp))
            AuthButton(
                text = "Sign In",
                loading = isLoading,
                success = isSignedIn,
                onClick = { onSignInClick(email, password) }
            )
        }
    }
}