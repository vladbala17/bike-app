package com.vlad.bikegarage.bikes.presentation.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vlad.bikegarage.R
import com.vlad.bikegarage.ui.theme.Blue
import com.vlad.bikegarage.ui.theme.GrayBlue

@Preview
@Composable
fun LinearProgressBar(
    progress: Float = 0.8f,
    progressColor: Color = Blue,
    backgroundColor: Color = GrayBlue,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Transparent)
            .height(25.dp),
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor)
                .height(6.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(progressColor)
                    .height(6.dp)
                    .fillMaxWidth(progress)
            )
            Image(
                painter = painterResource(id = R.drawable.loading_wrench),
                contentDescription = "loading wrench",
                modifier = Modifier
                    .fillMaxHeight()
                    .offset((-6).dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.loading_circle),
                contentDescription = "loading cercle",
                modifier = Modifier.fillMaxHeight()
            )
            Image(
                painter = painterResource(id = R.drawable.loading_bolt),
                contentDescription = "loading bolt",
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}