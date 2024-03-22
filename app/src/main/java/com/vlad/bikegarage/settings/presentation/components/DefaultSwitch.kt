package com.vlad.bikegarage.settings.presentation.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DefaultSwitch(checked: Boolean, onCheckedChanged: (Boolean) -> Unit, modifier: Modifier) {
    Switch(
        checked = checked, colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colors.onPrimary,
            checkedTrackColor = MaterialTheme.colors.primary,
            checkedTrackAlpha = 1f
        ), onCheckedChange = {
            onCheckedChanged(it)
        }, modifier = modifier
    )
}