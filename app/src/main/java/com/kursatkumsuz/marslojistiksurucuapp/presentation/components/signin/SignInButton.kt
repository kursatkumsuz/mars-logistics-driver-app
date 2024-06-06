package com.kursatkumsuz.marslojistiksurucuapp.presentation.components.signin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AuthButton(text: String, onClick: () -> Unit, loading: Boolean, success: Boolean) {

    val enterAnimation = fadeIn(
        animationSpec = tween(
            durationMillis = 1000,
        )
    ) + slideInVertically(
        initialOffsetY = { 120.dp.value.toInt() },
        animationSpec = tween(durationMillis = 1000)
    )

    val exitAnimation = fadeOut() + slideOutVertically { -80.dp.value.toInt() }

    Button(
        modifier = Modifier
            .height(60.dp)
            .padding(horizontal = 30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ),
        shape = CircleShape,
        onClick = onClick
    ) {

        AnimatedVisibility(
            visible = !loading && !success,
            exit = exitAnimation
        ) {
            Text(
                text = text,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.CenterVertically),
            visible = loading,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            Text(text = "Bekleyin...", color = Color.White)

        }
        AnimatedVisibility(
            visible = success,
            enter = enterAnimation
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Check Icon",
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Tamamlandı!", color = Color.White)
            }
        }
    }
}

