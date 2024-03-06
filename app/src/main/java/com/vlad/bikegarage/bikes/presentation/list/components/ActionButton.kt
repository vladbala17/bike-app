package com.vlad.bikegarage.bikes.presentation.list.components

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ActionButton(
    text: String = "Add bike",
    onButtonClick: () -> Unit = {},
    textStyle: TextStyle = MaterialTheme.typography.h3.copy(
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onPrimary
    ),
    modifier: Modifier = Modifier
) {
    Button(onClick = onButtonClick, modifier = modifier) {
        Text(
            text = text,
            style = textStyle,
            color = Color.White,
        )
    }
}