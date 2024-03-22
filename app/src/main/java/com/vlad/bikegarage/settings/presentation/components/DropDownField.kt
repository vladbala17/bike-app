package com.vlad.bikegarage.settings.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vlad.bikegarage.bikes.presentation.addbikes.components.TextTextField

@ExperimentalMaterialApi
@Composable
fun DropDownField(
    listItems: List<String>,
    selectedItem: String,
    onSelectedItem: (String) -> Unit,
    icon: Int,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = modifier
    ) {
        TextTextField(
            placeHolder = selectedItem,
            onValueChange = { newValue ->
                onSelectedItem(newValue)
            },
            readOnly = true,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(all = 16.dp)
                )
            },
            modifier = modifier
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = modifier
        ) {
            listItems.forEach { selectedOption ->
                DropdownMenuItem(
                    modifier = modifier,
                    onClick = {
                        onSelectedItem(selectedOption)
                        expanded = false
                    }
                ) {
                    Text(
                        text = selectedOption,
                        color = MaterialTheme.colors.onPrimary
                    )

                }
            }
        }

    }
}