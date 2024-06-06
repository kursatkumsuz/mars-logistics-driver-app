package com.kursatkumsuz.marslojistiksurucuapp.presentation.components.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalConfiguration


@Composable
fun Oval() {
    val screenHeight = LocalConfiguration.current.screenHeightDp / 2.9
    Canvas(modifier = Modifier.height(screenHeight.dp).fillMaxWidth()) {
        drawOval(
            color = Color(0xFF131313),
            topLeft = androidx.compose.ui.geometry.Offset(
                x = size.width * 0.25f,
                y = size.height * 0.25f
            ),
            size = androidx.compose.ui.geometry.Size(
                width = size.width * 0.5f,
                height = size.height * 0.5f
            )
        )
    }
}