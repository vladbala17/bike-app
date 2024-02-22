@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)

package com.vlad.bikegarage.bikes.presentation.addbikes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.presentation.addbikes.components.TextTextField
import com.vlad.bikegarage.bikes.presentation.components.ActionButton
import com.vlad.bikegarage.settings.presentation.DefaultSwitch
import com.vlad.bikegarage.settings.presentation.DropDownField
import com.vlad.bikegarage.settings.presentation.Label
import com.vlad.bikegarage.ui.theme.Brown
import com.vlad.bikegarage.ui.theme.CoralBlue
import com.vlad.bikegarage.ui.theme.Green
import com.vlad.bikegarage.ui.theme.LightBlue
import com.vlad.bikegarage.ui.theme.LightBrown
import com.vlad.bikegarage.ui.theme.Orange
import com.vlad.bikegarage.ui.theme.Pink
import com.vlad.bikegarage.ui.theme.Red
import com.vlad.bikegarage.ui.theme.SimpleGray
import com.vlad.bikegarage.ui.theme.SkyBlue
import com.vlad.bikegarage.ui.theme.White
import com.vlad.bikegarage.ui.theme.Yellow
import kotlin.math.absoluteValue

@Composable
fun AddBikesScreen(
    viewModel: AddBikeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.bike_app_background),
                contentScale = ContentScale.FillBounds
            )
    ) {
        HorizontalColorPicker()
        BikesPager()
        Label(title = stringResource(id = R.string.bike_name_label), isMandatory = true)
        TextTextField(
            placeHolder = stringResource(id = R.string.bike_name_label),
            text = state.value.bikeName,
            onValueChange = {
                viewModel.onEvent(AddBikeEvent.Submit(it))
            },
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true,
            isError = state.value.bikeNameError != null,
            errorMessage = state.value.bikeNameError,
        )
        Label(title = stringResource(R.string.wheel_size_label), isMandatory = true)
        DropDownField(
            listOf(
                "29'", "30'"
            ),
            selectedItem = "",
            onSelectedItem = { selectedItem ->
//                viewModel.onEvent(SettingsEvent.OnDistanceUnitSet(selectedItem))
            },
            R.drawable.icon_dropdown,
            modifier = Modifier.fillMaxWidth()
        )
        Label(title = stringResource(R.string.service_in_label), isMandatory = true)
        TextTextField(
            placeHolder = stringResource(id = R.string.bike_name_label),
            text = state.value.bikeName,
            onValueChange = {
                viewModel.onEvent(AddBikeEvent.Submit(it))
            },
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true,
            isError = state.value.bikeNameError != null,
            errorMessage = state.value.bikeNameError,
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.default_bike_label),
                modifier = Modifier.weight(1f)
            )
            DefaultSwitch(
                true,
                onCheckedChanged = {
//                    viewModel.onEvent(SettingsEvent.OnNotifyReminder)
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        ActionButton(
            text = stringResource(R.string.add_bike_label),
            onButtonClick = {},
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun HorizontalColorPicker(
    colorList: List<Color> = listOf(
        SimpleGray, Green, Red, Yellow, SkyBlue, Orange, CoralBlue,
        LightBrown, Pink, LightBlue, Brown
    ),
    onColorClicked: (clickedClicked: Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    CircularList(colorList = colorList, onItemClick = {}, modifier = Modifier.fillMaxWidth())
}

@Composable
fun CircularList(
    colorList: List<Color>,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit
) {
    val listState = rememberLazyListState(0)
    var selectedIndex by remember {
        mutableStateOf(-1)
    }
    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(colorList.size, itemContent = {
            val index = it % colorList.size
            // item composable
            Canvas(modifier = Modifier
                .size(20.dp)
                .border(
                    border =
                    if (selectedIndex == index) {
                        BorderStroke(2.dp, White)
                    } else {
                        BorderStroke(width = 0.dp, color = colorList[index])
                    }, shape = CircleShape
                )
                .selectable(
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                    }
                ), onDraw = {
                drawCircle(color = colorList[index])
            })
        })
    }
}

@Preview
@Composable
fun BikeCreation(
    bodyColor: Color = Color.Red,
    bikeName: String = "Electric bike",
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.bike_electric_big_wheels),
                contentDescription = "electric bike",
                modifier = Modifier.aspectRatio(16f / 9f),
            )
            Image(
                painter = painterResource(id = R.drawable.bike_electric_middle),
                contentDescription = "electric bike",
                colorFilter = ColorFilter.tint(bodyColor),
                modifier = Modifier.aspectRatio(16f / 9f)
            )
            Image(
                painter = painterResource(id = R.drawable.bike_electric_over),
                contentDescription = "electric bike",
                modifier = Modifier.aspectRatio(16f / 9f)
            )
        }

    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun BikesPager() {
    val pagerState = rememberPagerState(pageCount = { 4 })
    Column(
        modifier = Modifier.wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(start = 80.dp, end = 80.dp)
        ) { page ->
            BikeCreation(modifier = Modifier.graphicsLayer {
                val pageOffset = (
                        (pagerState.currentPage - page) + pagerState
                            .currentPageOffsetFraction
                        ).absoluteValue


                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            })
        }
        Text(text = "Electric Bike", color = White)
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(4) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(16.dp)
                )
            }
        }

    }
}

