package com.vlad.bikegarage.rides.presentation.addride

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.presentation.addbikes.components.TextTextField
import com.vlad.bikegarage.bikes.presentation.list.components.ActionButton
import com.vlad.bikegarage.notifications.BikeServiceWorker
import com.vlad.bikegarage.rides.presentation.addride.components.CustomDatePicker
import com.vlad.bikegarage.rides.presentation.addride.components.TimeDurationPicker
import com.vlad.bikegarage.settings.presentation.components.DropDownField
import com.vlad.bikegarage.settings.presentation.components.Label
import com.vlad.bikegarage.settings.presentation.components.NumericTextField
import com.vlad.bikegarage.util.Constants
import com.vlad.bikegarage.util.convertMillisToDateMonthNumber

@Preview
@ExperimentalMaterialApi
@Composable
fun AddRideScreen(
    rideId: Int = 0,
    viewModel: AddRideViewModel = hiltViewModel<AddRideViewModel, AddRideViewModel.AddRideViewModelFactory> { addRideViewModelFactory ->
        addRideViewModelFactory.create(rideId)
    },
    onAddRide: () -> Unit = {}
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Label(
            stringResource(R.string.ride_title_label),
            false,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        TextTextField(
            placeHolder = "",
            text = state.value.rideName,
            onValueChange = {
                viewModel.onEvent(AddRideEvent.OnRideTitleAdded(it))
            },
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            isError = state.value.rideNameError != null,
            errorMessage = state.value.rideNameError,
        )
        Label(
            stringResource(R.string.ride_bike_label),
            true,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        DropDownField(
            listItems = state.value.bikeNamesList.map { it.name },
            selectedItem = state.value.bikeName,
            onSelectedItem = { selectedItem ->
                viewModel.onEvent(AddRideEvent.OnBikeSelected(selectedItem))
            },
            R.drawable.icon_dropdown,
            modifier = Modifier.fillMaxWidth()
        )
        Label(
            stringResource(R.string.ride_distance_label),
            true,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        NumericTextField(
            value = state.value.distance.toString(),
            onServiceReminderAdded = { rideDistance ->
                viewModel.onEvent(AddRideEvent.OnRideDistanceAdded(rideDistance))
            }, modifier = Modifier.padding(bottom = 16.dp)
        )
        Label(
            stringResource(R.string.ride_duration_label),
            true,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        TextTextField(
            placeHolder = "",
            text = stringResource(
                id = R.string.ride_duration,
                state.value.durationHours,
                state.value.durationMinutes
            ),
            onValueChange = {
            },
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.onEvent(AddRideEvent.OnDurationClicked) },
            singleLine = true,
        )
        Label(
            stringResource(R.string.ride_date_label),
            true,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        TextTextField(
            placeHolder = "",
            text = convertMillisToDateMonthNumber(state.value.date),
            onValueChange = {
            },
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.onEvent(AddRideEvent.OnDateClicked) },
            singleLine = true,
        )
        Spacer(modifier = Modifier.weight(1f))
        ActionButton(

            text = if (rideId > 0) {
                stringResource(R.string.edit_bike_action)
            } else {
                stringResource(R.string.add_ride_label)
            },
            onButtonClick = {
                viewModel.onEvent(AddRideEvent.Submit)
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
    if (state.value.showDurationPicker) {
        TimeDurationPicker(
            hoursValue = state.value.durationHours,
            minutesValue = state.value.durationMinutes,
            onDismissRequest = { viewModel.onEvent(AddRideEvent.OnDismissDurationPicker) },
            onConfirmation = { hours, minutes ->
                viewModel.onEvent(AddRideEvent.OnDurationSet(hours, minutes))
            })
    }

    if (state.value.showDatePicker) {
        CustomDatePicker(onDateSelected = { date ->
            viewModel.onEvent(AddRideEvent.OnDateSet(date))
        }, onDismissDialog = {
            viewModel.onEvent(AddRideEvent.OnDismissDatePicker)
        })
    }

    if (state.value.isValidatedSuccessfully) {
        onAddRide()
        val inputData = Data.Builder().putString(Constants.BIKE_NAME_KEY, state.value.bikeName)
            .putString(Constants.RIDE_DISTANCE, state.value.distance).build()
        val defaultBikeRequest =
            OneTimeWorkRequestBuilder<BikeServiceWorker>().setInputData(inputData).setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST).build()
        WorkManager.getInstance(context).cancelAllWork()
        WorkManager.getInstance(context).enqueue(defaultBikeRequest)

    }
}
