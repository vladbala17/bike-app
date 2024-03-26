@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterialApi::class
)

package com.vlad.bikegarage.settings.presentation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vlad.bikegarage.R
import com.vlad.bikegarage.settings.presentation.components.DefaultSwitch
import com.vlad.bikegarage.settings.presentation.components.DropDownField
import com.vlad.bikegarage.settings.presentation.components.Label
import com.vlad.bikegarage.settings.presentation.components.NotificationPermissionTextProvider
import com.vlad.bikegarage.settings.presentation.components.NumericTextField
import com.vlad.bikegarage.settings.presentation.components.PermissionDialog
import com.vlad.bikegarage.ui.theme.BikeGarageTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    shouldShowRequestPermissionRationale:() -> Boolean
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    var hasNotificationPermission by remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else {
            mutableStateOf(true)
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasNotificationPermission = isGranted
            viewModel.onEvent(SettingsEvent.OnNotifyReminder(isGranted))
        })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondaryVariant)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Label(
            stringResource(R.string.distance_units_label),
            true,
            modifier = Modifier.padding(bottom = 4.dp)
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
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            NumericTextField(
                state.value.serviceIntervalReminder,
                onServiceReminderAdded = { distanceReminder ->
                    viewModel.onEvent(SettingsEvent.OnServiceIntervalReminderSet(distanceReminder))
                },
                state.value.distanceUnit,
                modifier = Modifier.weight(1f)
            )
            DefaultSwitch(
                state.value.isServiceNotifyEnabled,
                onCheckedChanged = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !hasNotificationPermission) {
                        viewModel.onEvent(SettingsEvent.OnShowPermissionDialog)
                    }
                    if (hasNotificationPermission) {
                        viewModel.onEvent(SettingsEvent.OnNotifyReminder(it))
                    }
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Label(
            stringResource(R.string.default_bike_label),
            true,
            modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
        )
        DropDownField(
            state.value.defaultBikeList,
            selectedItem = state.value.defaultBike,
            onSelectedItem = { selectedItem ->
                viewModel.onEvent(SettingsEvent.OnDefaultBikeSet(selectedItem))
            },
            R.drawable.icon_dropdown,
            modifier = Modifier.fillMaxWidth()
        )

    }
    if (state.value.showPermissionDialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !hasNotificationPermission) {
            PermissionDialog(
                permissionTextProvider = NotificationPermissionTextProvider(),
                isPermanentlyDeclined = shouldShowRequestPermissionRationale(),
                permanentlyDeclinedText = stringResource(id = R.string.permission_permanently_declined),
                permissionRationale = stringResource(id = R.string.permission_rationale),
                onDismiss = {
                    viewModel.onEvent(SettingsEvent.OnDismissPermissionDialog)
                },
                onOkClick = {
                    permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    viewModel.onEvent(SettingsEvent.OnDismissPermissionDialog)
                },
                onGoToAppSettingsClick = {
                    openAppSettings(context)
                    viewModel.onEvent(SettingsEvent.OnDismissPermissionDialog)
                }
            )
        }
    }
}


@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun Development() {
    BikeGarageTheme {
        Label("Required Name", isMandatory = true, modifier = Modifier.fillMaxWidth())
    }
}


@Preview(showBackground = true)
@Composable
fun Development2() {
    BikeGarageTheme {
        DropDownField(
            listItems = listOf(),
            selectedItem = "text",
            onSelectedItem = {},
            icon = R.drawable.icon_dropdown
        )
    }
}

fun openAppSettings(context: Context) {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", context.packageName, null)
    )

    context.startActivity(intent)
}