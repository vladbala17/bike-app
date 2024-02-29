@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)

package com.vlad.bikegarage.bikes.presentation.addbikes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.domain.model.BikeType
import com.vlad.bikegarage.bikes.presentation.addbikes.components.BikesPager
import com.vlad.bikegarage.bikes.presentation.addbikes.components.ElectricBike
import com.vlad.bikegarage.bikes.presentation.addbikes.components.HorizontalColorPicker
import com.vlad.bikegarage.bikes.presentation.addbikes.components.HybridBike
import com.vlad.bikegarage.bikes.presentation.addbikes.components.MTBBike
import com.vlad.bikegarage.bikes.presentation.addbikes.components.RoadBike
import com.vlad.bikegarage.bikes.presentation.addbikes.components.TextTextField
import com.vlad.bikegarage.bikes.presentation.components.ActionButton
import com.vlad.bikegarage.settings.presentation.DefaultSwitch
import com.vlad.bikegarage.settings.presentation.DropDownField
import com.vlad.bikegarage.settings.presentation.Label
import com.vlad.bikegarage.settings.presentation.NumericTextField

@Composable
fun AddBikesScreen(
    viewModel: AddBikeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onAddBike: () -> Unit
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(4.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        HorizontalColorPicker(onColorClicked = { color ->
            viewModel.onEvent(AddBikeEvent.OnColorPick(color))
        })
        BikesPager(
            bikesList = viewModel.state.value.bikePagerList,
            bikeName = state.value.bikeTitle,
            onPageChanged = { page ->
                viewModel.onEvent(AddBikeEvent.OnPageSelected(page))
            })
        Label(
            title = stringResource(id = R.string.bike_name_label),
            isMandatory = true,
            modifier = Modifier.fillMaxWidth()
        )
        TextTextField(
            placeHolder = "",
            text = state.value.bikeName,
            onValueChange = {
                viewModel.onEvent(AddBikeEvent.OnBikeNameAdded(it))
            },
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            isError = state.value.bikeNameError != null,
            errorMessage = state.value.bikeNameError,
        )
        Label(title = stringResource(R.string.wheel_size_label), isMandatory = true)
        DropDownField(
            listOf(
                "29'", "30'"
            ),
            selectedItem = state.value.wheelSize,
            onSelectedItem = { selectedItem ->
                viewModel.onEvent(AddBikeEvent.OnWheelSizeAdded(selectedItem))
            },
            R.drawable.icon_dropdown,
            modifier = Modifier.fillMaxWidth()
        )
        Label(title = stringResource(R.string.service_in_label), isMandatory = true)
        NumericTextField(
            value = state.value.serviceIn,
            onServiceReminderAdded = { serviceInterval ->
                viewModel.onEvent(AddBikeEvent.OnServiceIntervalAdded(serviceInterval))
            })
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.default_bike_label),
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            DefaultSwitch(
                checked = state.value.isDefault,
                onCheckedChanged = { isDefault ->
                    viewModel.onEvent(AddBikeEvent.OnDefaultBikeAdded(isDefault))
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        ActionButton(
            text = stringResource(R.string.add_bike_label),
            onButtonClick = {
                viewModel.onEvent(AddBikeEvent.Submit)
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
    if (state.value.isValidatedSuccessfully) {
        onAddBike()
    }
}

@Preview
@Composable
fun BikeCreation(
    bodyColor: Color = Color.Red,
    bodyType: BikeType = BikeType.Electric,
    modifier: Modifier = Modifier,
    onPageChanged: () -> Unit = {}
) {
    createBikeType(bodyType, bodyColor = bodyColor, modifier = modifier)
}

@Composable
fun createBikeType(type: BikeType = BikeType.Electric, modifier: Modifier, bodyColor: Color) {
    when (type) {
        BikeType.Electric -> ElectricBike(modifier = modifier, bodyColor = bodyColor)
        BikeType.Hybrid -> HybridBike(modifier = modifier, bodyColor = bodyColor)
        BikeType.MTB -> MTBBike(modifier = modifier, bodyColor = bodyColor)
        BikeType.RoadBike -> RoadBike(modifier = modifier, bodyColor = bodyColor)
    }
}

