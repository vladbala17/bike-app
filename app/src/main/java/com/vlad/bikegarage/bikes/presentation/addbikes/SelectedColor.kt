package com.vlad.bikegarage.bikes.presentation.addbikes

import androidx.compose.ui.graphics.Color

class SelectedColor(color: Color, private val isSelected: Boolean) {
    var selected = mutableSetOf(isSelected)
}