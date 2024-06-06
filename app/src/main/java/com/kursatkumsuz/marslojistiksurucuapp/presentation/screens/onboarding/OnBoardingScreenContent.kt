package com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kursatkumsuz.marslojistiksurucuapp.R

@Composable
fun OnBoardingContent(onBoardingPage: OnBoardingPage, pageOffset: Float) {
    val screenWidth = LocalConfiguration.current.screenWidthDp / 2
    val contentHeight = (LocalConfiguration.current.screenHeightDp / 3 * 2.5).dp
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0x0),
            Color(0x1A000000),
            Color(0x4D000000),
            Color(0xB3000000),
            Color(0xFF000000)
        )
    )
    Card {
        Column(
            modifier = Modifier
                .height(contentHeight)
                .background(Color.Black)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset {
                            IntOffset(
                                x = (screenWidth.dp * pageOffset).roundToPx(),
                                y = 0,
                            )
                        },
                    painter = painterResource(id = onBoardingPage.image),
                    contentDescription = "OnBoarding Image",
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = gradient)
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = onBoardingPage.title),
                        color = Color.White,
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        modifier = Modifier.width(250.dp),
                        text = stringResource(id = onBoardingPage.description),
                        color = Color.LightGray
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StartButton(pagerState: PagerState, onStartClick: () -> Unit) {
    AnimatedVisibility(
        visible = pagerState.currentPage == 2,
        enter = expandVertically(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    ) {
        Button(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            onClick = onStartClick,
            contentPadding = PaddingValues(5.dp)
        ) {
            Text(
                text = stringResource(id = R.string.button_start),
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}