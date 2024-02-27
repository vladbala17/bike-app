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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
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
import com.vlad.bikegarage.bikes.domain.model.BikeType
import com.vlad.bikegarage.bikes.domain.model.PagerBike
import com.vlad.bikegarage.bikes.presentation.addbikes.components.TextTextField
import com.vlad.bikegarage.bikes.presentation.components.ActionButton
import com.vlad.bikegarage.settings.presentation.DefaultSwitch
import com.vlad.bikegarage.settings.presentation.DropDownField
import com.vlad.bikegarage.settings.presentation.Label
import com.vlad.bikegarage.settings.presentation.NumericTextField
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
            .background(MaterialTheme.colors.background)
            .padding(4.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        HorizontalColorPicker(onColorClicked = { color ->
            viewModel.onEvent(AddBikeEvent.OnColorPick(color))
        })
        BikesPager(
            bikesList = viewModel.state.value.bikePagerList,
            bikeName = state.value.bikeTitle,
            onPageChanged = { page ->
                viewModel.onEvent(AddBikeEvent.OnPageSelected(page))
            })
        Label(
            title = stringResource(id = R.string.bike_name_label),
            isMandatory = true,
            modifier = Modifier.fillMaxWidth()
        )
        TextTextField(
            placeHolder = "",
            text = state.value.bikeName,
            onValueChange = {
                viewModel.onEvent(AddBikeEvent.Submit(it))
            },
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            modifier = Modifier
                .fillMaxWidth(),
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
        NumericTextField(
            value = "100",
            onServiceReminderAdded = { distanceReminder ->
//                viewModel.onEvent(SettingsEvent.OnServiceIntervalReminderSet(distanceReminder))
            },

            )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.default_bike_label),
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
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
    CircularList(
        colorList = colorList,
        onItemClick = onColorClicked,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun CircularList(
    colorList: List<Color>,
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit
) {
    val listState = rememberLazyListState(0)
    var selectedIndex by remember {
        mutableStateOf(-1)
    }
    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(36.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(colorList.size, itemContent = {
            val index = it % colorList.size
            // item composable
            Canvas(modifier = Modifier
                .size(25.dp)
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
                        onItemClick(colorList[index].toArgb())
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
    bodyType: BikeType = BikeType.Electric,
    modifier: Modifier = Modifier,
    onPageChanged: () -> Unit = {}
) {
    createBikeType(bodyType, bodyColor = bodyColor, modifier = modifier)
}

@Composable
fun createBikeType(type: BikeType = BikeType.Electric, modifier: Modifier, bodyColor: Color) {
    when (type) {
        BikeType.Electric -> createElectricBike(modifier = modifier, bodyColor = bodyColor)
        BikeType.Hybrid -> createHybridBike(modifier = modifier, bodyColor = bodyColor)
        BikeType.MTB -> createMTBBike(modifier = modifier, bodyColor = bodyColor)
        BikeType.RoadBike -> createRoadBike(modifier = modifier, bodyColor = bodyColor)
    }
}

@Composable
fun createElectricBike(bodyColor: Color = Color.Red, modifier: Modifier) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.bike_electric_big_wheels),
            contentDescription = stringResource(R.string.electric_bike_name),
            modifier = Modifier.aspectRatio(16f / 9f),
        )
        Image(
            painter = painterResource(id = R.drawable.bike_electric_middle),
            contentDescription = stringResource(R.string.electric_bike_name),
            colorFilter = ColorFilter.tint(bodyColor),
            modifier = Modifier.aspectRatio(16f / 9f)
        )
        Image(
            painter = painterResource(id = R.drawable.bike_electric_over),
            contentDescription = stringResource(R.string.electric_bike_name),
            modifier = Modifier.aspectRatio(16f / 9f)
        )
    }
}

@Composable
fun createMTBBike(bodyColor: Color = Color.Red, modifier: Modifier) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.bike_mtb_big_wheels),
            contentDescription = stringResource(R.string.mtb_bike_name),
            modifier = Modifier.aspectRatio(16f / 9f),
        )
        Image(
            painter = painterResource(id = R.drawable.bike_mtb_middle),
            contentDescription = stringResource(R.string.mtb_bike_name),
            colorFilter = ColorFilter.tint(bodyColor),
            modifier = Modifier.aspectRatio(16f / 9f)
        )
        Image(
            painter = painterResource(id = R.drawable.bike_mtb_over),
            contentDescription = stringResource(R.string.mtb_bike_name),
            modifier = Modifier.aspectRatio(16f / 9f)
        )
    }
}

@Composable
fun createRoadBike(bodyColor: Color = Color.Red, modifier: Modifier) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.bike_roadbike_big_wheels),
            contentDescription = stringResource(R.string.road_bike_name),
            modifier = Modifier.aspectRatio(16f / 9f),
        )
        Image(
            painter = painterResource(id = R.drawable.bike_roadbike_middle),
            contentDescription = stringResource(R.string.road_bike_name),
            colorFilter = ColorFilter.tint(bodyColor),
            modifier = Modifier.aspectRatio(16f / 9f)
        )
        Image(
            painter = painterResource(id = R.drawable.bike_roadbike_over),
            contentDescription = stringResource(R.string.road_bike_name),
            modifier = Modifier.aspectRatio(16f / 9f)
        )
    }
}

@Composable
fun createHybridBike(bodyColor: Color = Color.Red, modifier: Modifier) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.bike_hybrid_big_wheels),
            contentDescription = stringResource(R.string.hybrid_bike_name),
            modifier = Modifier.aspectRatio(16f / 9f),
        )
        Image(
            painter = painterResource(id = R.drawable.bike_hybrid_middle),
            contentDescription = stringResource(R.string.hybrid_bike_name),
            colorFilter = ColorFilter.tint(bodyColor),
            modifier = Modifier.aspectRatio(16f / 9f)
        )
        Image(
            painter = painterResource(id = R.drawable.bike_hybrid_over),
            contentDescription = stringResource(R.string.hybrid_bike_name),
            modifier = Modifier.aspectRatio(16f / 9f)
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun BikesPager(
    bikesList: List<PagerBike>,
    bikeName: String = BikeType.Electric.type,
    onPageChanged: (Int) -> Unit = {}
) {
    val pagerState =
        rememberPagerState(pageCount = { bikesList.size }, initialPage = bikesList.size / 2)
    LaunchedEffect(key1 = pagerState, block = {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onPageChanged(page)
        }
    })
    Column(
        modifier = Modifier.wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(start = 80.dp, end = 80.dp)
        ) { page ->
            BikeCreation(
                modifier = Modifier.graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue


                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                },
                bodyColor = Color(bikesList[page].color),
                bodyType = bikesList[page].type
            )
        }
        Text(text = bikeName, color = White)
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp, top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(4) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) MaterialTheme.colors.primary else MaterialTheme.colors.onPrimary
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }

    }
}

