package com.vlad.bikegarage.bikes.presentation.list.components

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vlad.bikegarage.R

@Preview
@Composable
fun CardDropDownMenu(
    displayMenu: Boolean = false,
    modifier: Modifier = Modifier,
    itemId: Int = 0,
    itemName: String = "",
    onDismissRequest: () -> Unit = {},
    onEditItemClick: (Int) -> Unit = {},
    onDeleteItemClick: (String) -> Unit = {}
) {
    DropdownMenu(expanded = displayMenu, onDismissRequest = onDismissRequest) {
        DropdownMenuItem(onClick = { onEditItemClick(itemId) }) {
            Icon(
                painter = painterResource(id = R.drawable.icon_edit),
                contentDescription = "card menu edit",
            )
            Text(text = stringResource(R.string.edit_menu_action))
        }
        DropdownMenuItem(onClick = {onDeleteItemClick(itemName) }) {
            Icon(
                painter = painterResource(id = R.drawable.icon_delete),
                contentDescription = "card menu delete",
            )
            Text(text = stringResource(R.string.delete_menu_action))
        }
    }
}