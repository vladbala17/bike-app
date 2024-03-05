package com.vlad.bikegarage.bikes.presentation.components

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vlad.bikegarage.R

@Preview
@Composable
fun CardDropDownMenu(
    displayMenu: Boolean = false,
    modifier: Modifier = Modifier,
    bikeId: Int = 0,
    onDismissRequest: () -> Unit = {},
    onEditBikeClick: (Int) -> Unit = {},
    onDeleteBikeClick: () -> Unit = {}
) {
    DropdownMenu(expanded = displayMenu, onDismissRequest = onDismissRequest) {
        DropdownMenuItem(onClick = {onEditBikeClick(bikeId)}) {
            Text(text = stringResource(R.string.edit_menu_action))
        }
        DropdownMenuItem(onClick = { }) {
            Text(text = stringResource(R.string.delete_menu_action))
        }
    }
}