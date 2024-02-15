package com.vlad.bikegarage.bikes.presentation.addbikes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Composable
fun AddBikesScreen() {
    HorizontalColorPicker()
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
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(colorList.size, itemContent = {
            val index = it % colorList.size
            // item composable
            Canvas(modifier = Modifier
                .size(36.dp)
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
fun testingSelection() {

}

