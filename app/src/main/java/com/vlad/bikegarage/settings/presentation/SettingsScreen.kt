@file:OptIn(ExperimentalMaterialApi::class)

package com.vlad.bikegarage.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vlad.bikegarage.R
import com.vlad.bikegarage.ui.theme.BikeGarageTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsScreen() {
    Column(modifier = Modifier.fillMaxSize().background(colorResource(R.color.deep_dark_blue)).padding(16.dp)) {
           Label(stringResource(R.string.distance_units_label), true)
           DropDownField(listOf("KM", "Miles"), R.drawable.icon_dropdown, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun Label(title: String = "", isMandatory: Boolean = false, modifier: Modifier = Modifier) {
    Row {
        Text(
            text = title,
            style = MaterialTheme.typography.h1,
            color = colorResource(R.color.gray)
        )
        if (isMandatory) {
            Icon(
                painter = painterResource(R.drawable.icon_required),
                contentDescription = stringResource(R.string.distance_units_label),
                tint = colorResource(R.color.gray)
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun DropDownField(listItems: List<String>, icon: Int, modifier: Modifier = Modifier) {
    var expanded by remember {
        mutableStateOf(false)
    }

    var selectedItem by remember {
        mutableStateOf(listItems.first())
    }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            modifier = modifier,
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                painterResource(icon)
            }
        )

        ExposedDropdownMenu(
            modifier = modifier,
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { selectedOption ->
                DropdownMenuItem(
                    modifier = modifier,
                    onClick = {
                        selectedItem = selectedOption
                        expanded = false
                    }
                ) {
                    Text(text = selectedOption)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun Development() {
    BikeGarageTheme {
        val dropdownList = listOf("KM", "Miles")

        DropDownField(dropdownList, R.drawable.icon_dropdown)
    }
}

@Preview(showBackground = true)
@Composable
fun Development2() {
    BikeGarageTheme {
        SettingsScreen()
    }
}