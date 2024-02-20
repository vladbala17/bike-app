@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterialApi::class
)

package com.vlad.bikegarage.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vlad.bikegarage.R
import com.vlad.bikegarage.ui.theme.BikeGarageTheme
import com.vlad.bikegarage.util.SuffixTransformation

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondaryVariant)
            .padding(16.dp)
    ) {
        Label(
            stringResource(R.string.distance_units_label),
            true,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        DropDownField(
            listOf(
                stringResource(id = R.string.distance_unit_km),
                stringResource(id = R.string.distance_unit_miles)
            ),
            selectedItem = state.value.distanceUnit,
            onSelectedItem = { selectedItem ->
                viewModel.onEvent(SettingsEvent.OnDistanceUnitSet(selectedItem))
            },
            R.drawable.icon_dropdown,
            modifier = Modifier.fillMaxWidth()
        )
        Label(
            stringResource(R.string.service_reminder_label),
            false,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            NumericTextField(
                state.value.serviceIntervalReminder,
                onServiceReminderAdded = { distanceReminder ->
                    viewModel.onEvent(SettingsEvent.OnServiceIntervalReminderSet(distanceReminder))
                },
                modifier = Modifier.weight(1f)
            )
            DefaultSwitch(
                state.value.isServiceNotifyEnabled,
                onCheckedChanged = {
                    viewModel.onEvent(SettingsEvent.OnNotifyReminder)
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Label(
            stringResource(R.string.default_bike_label),
            true,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        DropDownField(
            listOf(
                "Bike 1",
                "Bike 2",
                "Bike 3",
                "Bike 4",
                "Bike 5",
            ),
            selectedItem = state.value.defaultBike,
            onSelectedItem = { selectedItem ->
                viewModel.onEvent(SettingsEvent.OnDefaultBikeSet(selectedItem))
            },
            R.drawable.icon_dropdown,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun Label(title: String = "", isMandatory: Boolean = false, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSecondary,
        )
        if (isMandatory) {
            Icon(
                painter = painterResource(R.drawable.icon_required),
                contentDescription = stringResource(R.string.distance_units_label),
                tint = MaterialTheme.colors.onSecondary
            )
        }
    }
}

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
        TextField(
            value = selectedItem,
            onValueChange = { newValue ->
                onSelectedItem(newValue)
            },
            readOnly = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
            ),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = Color.White
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
                        color = Color.White
                    )

                }
            }
        }

    }
}

@Composable
fun NumericTextField(
    value: String = "100",
    onServiceReminderAdded: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
    ) {
        TextField(
            value = value,
            onValueChange = {
                onServiceReminderAdded(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
            ),
            visualTransformation = SuffixTransformation(" km"),
            modifier = modifier
        )
    }
}

@Composable
fun DefaultSwitch(checked: Boolean, onCheckedChanged: (Boolean) -> Unit, modifier: Modifier) {

    Switch(checked = checked, colors = SwitchDefaults.colors(
        checkedThumbColor = MaterialTheme.colors.onPrimary,
        checkedTrackColor = MaterialTheme.colors.primary,
        checkedTrackAlpha = 1f
    ), onCheckedChange = {
        onCheckedChanged(it)
    }, modifier = modifier
    )
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun Development() {
    BikeGarageTheme {
//        NumericTextField(Modifier.fillMaxWidth())
    }
}


@Preview(showBackground = true)
@Composable
fun Development2() {
    BikeGarageTheme {
        SettingsScreen()
    }
}