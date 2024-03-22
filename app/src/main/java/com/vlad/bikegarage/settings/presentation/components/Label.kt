package com.vlad.bikegarage.settings.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.vlad.bikegarage.R

@Composable
fun Label(title: String = "", isMandatory: Boolean = false, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        if (isMandatory) {
            Icon(
                painter = painterResource(R.drawable.icon_required),
                contentDescription = stringResource(R.string.distance_units_label),
                tint = MaterialTheme.colors.onSecondary,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}