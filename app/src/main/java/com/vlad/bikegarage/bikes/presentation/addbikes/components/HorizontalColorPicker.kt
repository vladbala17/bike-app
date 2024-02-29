package com.vlad.bikegarage.bikes.presentation.addbikes.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
import com.vlad.bikegarage.ui.theme.Yellow

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