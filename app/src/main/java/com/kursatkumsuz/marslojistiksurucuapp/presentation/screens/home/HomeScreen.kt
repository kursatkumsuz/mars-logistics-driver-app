package com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.kursatkumsuz.marslojistiksurucuapp.R
import com.kursatkumsuz.marslojistiksurucuapp.presentation.components.home.TableRow
import com.kursatkumsuz.marslojistiksurucuapp.presentation.components.home.Oval
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    onNavigateToDetailScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF131313))
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
            Image(
                painter = painterResource(id = R.drawable.pic_profile),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Merhaba Hamza",
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        CategoryTabRow(onItemClick = {}, onNavigateToDetailScreen = onNavigateToDetailScreen)
    }
}


@OptIn(ExperimentalPagerApi::class)
@ExperimentalFoundationApi
@Composable
fun CategoryTabRow(
    onItemClick: (Int) -> Unit, onNavigateToDetailScreen: () -> Unit
) {

    val days = listOf(
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
    )
    val pagerState = rememberPagerState()
    val tabRowIndicator = @Composable { tabPositions: List<TabPosition> ->
        TabRowIndicator(tabPositions, pagerState)
    }
    var selectedCategory by remember {
        mutableStateOf("1th Day")
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF131313))
    ) {
        ItemPager(
            pagerState,
            category = selectedCategory,
            count = days.size,
            onItemClick = { onNavigateToDetailScreen() },
        )
        Oval()
        ScrollableTabRow(
            modifier = Modifier
                .padding(top = 33.dp, start = 15.dp)
                .wrapContentSize(),
            selectedTabIndex = pagerState.currentPage,
            indicator = tabRowIndicator,
            containerColor = Color(0xFF131313),
            edgePadding = 0.dp,
            divider = {
                Divider(color = Transparent)
            }
        ) {
            days.fastForEachIndexed { index, names ->
                TabItem(pagerState, index, names) { category ->
                    selectedCategory = category
                }
            }
        }

    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabItem(
    pagerState: PagerState,
    index: Int,
    day: String,
    onChangeCategory: (String) -> Unit
) {
    var isClick by remember {
        mutableStateOf(false)
    }
    var isInitial by remember {
        mutableStateOf(true)
    }
    val isSelected = pagerState.currentPage == index
    val color: Color by animateColorAsState(
        if (isSelected) Black else White, label = ""
    )
    if (isSelected)
        onChangeCategory(day)

    Tab(
        modifier = Modifier.zIndex(1f),
        text = {
            Column(
                modifier = Modifier
                    .background(
                        if (!isSelected) Color(0xFF333333) else Transparent,
                        shape = CircleShape
                    )

            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            vertical = if (!isSelected) 15.dp else 25.dp,
                            horizontal = if (!isSelected) 10.dp else 0.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {


                    Row {
                        Text(
                            text = day, color = color, fontSize = 18.sp
                        )
                        Text(
                            text = "th", color = color, fontSize = 14.sp
                        )
                    }
                    Text(text = "Day", color = color, fontSize = 18.sp)
                }
            }
        },
        selected = isSelected,
        onClick = { isClick = !isClick }
    )

    LaunchedEffect(key1 = isClick) {
        if (isInitial) {
            pagerState.scrollToPage(0)
            isInitial = false
        } else {
            pagerState.scrollToPage(index)
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabRowIndicator(tabPositions: List<TabPosition>, pagerState: PagerState) {

    val transition = updateTransition(pagerState.currentPage, label = "")
    val startIndicator by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(
                    dampingRatio = 0.5f,
                    stiffness = Spring.StiffnessVeryLow
                )
            } else {
                spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            }
        },
        label = ""
    ) {
        tabPositions[it].left
    }

    val endIndicator by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            } else {
                spring(
                    dampingRatio = 0.5f,
                    stiffness = Spring.StiffnessVeryLow
                )
            }
        },
        label = ""
    ) {
        tabPositions[it].right
    }
    Box(
        modifier = Modifier
            .offset(x = startIndicator)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(endIndicator - startIndicator)
            .fillMaxSize()
            .padding(horizontal = 7.dp, vertical = 5.dp)
            .background(color = White, shape = CircleShape)
    )

}


@OptIn(ExperimentalPagerApi::class)
@ExperimentalFoundationApi
@Composable
fun ItemPager(
    pagerState: PagerState,
    count: Int,
    category: String,
    onItemClick: () -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp / 1.4
    HorizontalPager(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF131313)),
        count = count,
        state = pagerState,
    ) { page ->
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 50.dp)
                    .fillMaxWidth()
                    .height(screenHeight.dp)
                    .background(White, shape = RoundedCornerShape(topEnd = 50.dp, topStart = 50.dp))
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffset
                                ).absoluteValue
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    },
                contentAlignment = Alignment.TopCenter
            ) {
                LessonList(onNavigateToDetailScreen = { onItemClick() })
            }
        }
    }
}

@Composable
fun LessonList(
    onNavigateToDetailScreen: () -> Unit
) {

    val colors = listOf(
        Color(0xFFEC4D4D),
        Color(0xFFEC4DC6),
        Color(0xFFE524DEC),
        Color(0xFECC85CA),
        Color(0xFE44B685),
    )
    val names = listOf(
        "Adjectives",
        "Adverbs",
        "Nouns",
        "Verbs",
        "Conjunctions"
    )
    val progresses = listOf(
        0.7f,
        0.5f,
        0.9f,
        0.3f,
        0.6f,
    )
    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(70.dp))
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Pozisyonlar",
                        color = Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = "2 Pozisyon bulundu.",
                        color = LightGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                TableRow()
            }
        }
        items(names.size) { index ->
            ListItem(
                color = colors[index],
                name = names[index],
                progress = progresses[index],
                onNavigateToDetailScreen = onNavigateToDetailScreen
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
fun ListItem(color: Color, name: String, progress: Float, onNavigateToDetailScreen: () -> Unit) {

    val lastProgress = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = Unit) {
        lastProgress.animateTo(targetValue = progress, animationSpec = tween(1000))
    }

    Box(
        modifier = Modifier
            .clickable {
                onNavigateToDetailScreen()
            }
            .padding(10.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(color = Black, shape = CircleShape)
                    .size(44.dp)
            ) {
                Text(
                    text = "1",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 22.sp,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = "P2165465456152", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Istanbul -> Ankara",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "03.06.2022",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.LightGray
                )
            }
            Box(modifier = Modifier.size(height = 40.dp, width = 90.dp))
        }
    }
}