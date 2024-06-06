package com.kursatkumsuz.marslojistiksurucuapp.presentation.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TableRow() {
    var selectedIndex by remember { mutableStateOf(0) }
    var visible by remember { mutableStateOf(true) }
    val list = listOf("Planlanan", "Tamamlanan")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TabRow(
            selectedTabIndex = selectedIndex,
            containerColor = Color.Black,
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .clip(CircleShape),
            indicator = { tabPositions: List<TabPosition> -> }
        ) {
            list.forEachIndexed { index, text ->
                val selected = selectedIndex == index
                Tab(
                    modifier = if (selected) Modifier
                        .padding(horizontal = 3.dp)
                        .size(width = 95.dp, height = 45.dp)
                        .clip(CircleShape)
                        .background(
                            Color.White
                        )
                    else Modifier
                        .padding(horizontal = 3.dp)
                        .clip(CircleShape)
                        .size(width = 95.dp, height = 45.dp)
                        .background(
                            Color.Black
                        ),
                    selected = selected,
                    onClick = {
                        selectedIndex = index
                        visible = selectedIndex == 0
                    },
                    text = { Text(text = text, color = if (selected) Color.Black else Color.White, fontSize = 10.sp) }
                )
            }
        }
    }
}