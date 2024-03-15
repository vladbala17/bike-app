package com.vlad.bikegarage.rides.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vlad.bikegarage.R

@Preview
@Composable
fun BarChart(
    inputData: List<Int> = listOf(100, 14, 50, 23),
    maxHeight: Dp = 200.dp,
    modifier: Modifier = Modifier
) {

    val borderColor = MaterialTheme.colors.primary
    val strokeWidth = 1.dp


    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(id = R.drawable.icon_stats),
                contentDescription = "ride card menu icon",
            )
            Text(
                text = stringResource(R.string.chart_title),
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Row(
            modifier = modifier.then(
                Modifier
                    .fillMaxWidth()
                    .height(maxHeight)
                    .drawBehind {
                        // X- Axis

                        drawLine(
                            color = borderColor,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 2.dp.toPx()
                        )

                        // Y- Axis
                        drawLine(
                            color = borderColor,
                            start = Offset(0f, 0f),
                            end = Offset(0f, size.height),
                            strokeWidth = strokeWidth.toPx()
                        )
                        repeat(11) {

                        drawLine(
                            color = borderColor,
                            start = Offset(0f, size.height - it*size.height/10),
                            end = Offset(size.width, size.height - it*size.height/10),
                            strokeWidth = strokeWidth.toPx()
                        )
                        }
                    },
            ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            inputData.forEach { item ->
                Bar(
                    value = item.toFloat(),
                    color = MaterialTheme.colors.primary,
                    maxHeight = maxHeight
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().border(width = 1.dp, Color.Red),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            inputData.forEach { item ->
                Text(
                    text = item.toString(),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.25f)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.total_chart_label),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = stringResource(id = R.string.total_km_chart_label, "25.570"),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onPrimary,
            )
        }

    }

}

@Composable
private fun RowScope.Bar(
    value: Float,
    color: Color,
    maxHeight: Dp
) {

    val itemHeight = remember(value) { value * maxHeight.value / 100 }

        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .padding(horizontal = 5.dp)
                .weight(1f)
                .height(itemHeight.dp)
                .background(color)
        ) {
            Text(text = "test", Modifier.align(Alignment.BottomCenter))
        }
}




