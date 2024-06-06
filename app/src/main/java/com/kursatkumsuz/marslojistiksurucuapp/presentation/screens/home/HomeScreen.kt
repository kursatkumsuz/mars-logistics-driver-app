package com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.kursatkumsuz.marslojistiksurucuapp.R
import com.kursatkumsuz.marslojistiksurucuapp.domain.model.DeliveryOrder
import com.kursatkumsuz.marslojistiksurucuapp.presentation.components.home.TableRow
import com.kursatkumsuz.marslojistiksurucuapp.presentation.components.home.Oval
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    onNavigateToDetailScreen: (Long) -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state = viewModel.uiState

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

        state.dataList?.let {
            CategoryTabRow(
                data = it,
                onNavigateToDetailScreen = onNavigateToDetailScreen
            )
        }

    }
}


@OptIn(ExperimentalPagerApi::class)
@ExperimentalFoundationApi
@Composable
fun CategoryTabRow(
    data: List<DeliveryOrder>,
    onNavigateToDetailScreen: (Long) -> Unit
) {

    val pagerState = rememberPagerState()
    val tabRowIndicator = @Composable { tabPositions: List<TabPosition> ->
        TabRowIndicator(tabPositions, pagerState)
    }
    var selectedDate by remember {
        mutableStateOf(data[0].date)
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF131313))
    ) {
        selectedDate?.let {
            ItemPager(
                pagerState,
                date = it,
                count = data.size,
                data = data,
                onItemClick = onNavigateToDetailScreen,
            )
        }
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
            val indexedData = data.withIndex()
            val groupedData = indexedData.groupBy { it.value.date }

            groupedData.values.map { group ->
                val firstItem = group.firstOrNull()
                firstItem?.let { item ->
                    item.value.date?.let {
                        TabItem(pagerState = pagerState, index = item.index, date = it) {
                            selectedDate = item.value.date
                        }
                    }
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
    date: String,
    onChangeDate: (String) -> Unit
) {

    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(date, dateFormatter)

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
        onChangeDate(date.toString())

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


                    Text(
                        text = date.dayOfMonth.toString(), color = color, fontSize = 18.sp
                    )

                    Text(text = date.month.name.toLowerCase(), color = color, fontSize = 18.sp)
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
    data: List<DeliveryOrder>,
    date: String,
    onItemClick: (Long) -> Unit
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
                val filteredData = data.filter { it -> it.date == date }
                PositionList(dataList = filteredData, onNavigateToDetailScreen = onItemClick)
            }
        }
    }
}

@Composable
fun PositionList(
    dataList: List<DeliveryOrder>,
    onNavigateToDetailScreen: (Long) -> Unit
) {

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
                        text = "${dataList.size} Pozisyon bulundu.",
                        color = LightGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                TableRow()
            }
        }
        items(dataList.size) { index ->
            ListItem(
                index = index + 1,
                data = dataList[index],
                onNavigateToDetailScreen = onNavigateToDetailScreen
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
fun ListItem(
    index: Int,
    data: DeliveryOrder,
    onNavigateToDetailScreen: (Long) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable {
                data.orderNo?.let { onNavigateToDetailScreen(it) }
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
                    text = index.toString(),
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 22.sp,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = "P${data.orderNo}", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${data.departurePointName} -> ${data.arrivalPointName}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = data.date ?: "Bilinmeyen Tarih",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = LightGray
                )
            }
            Box(modifier = Modifier.size(height = 40.dp, width = 90.dp))
        }
    }
}