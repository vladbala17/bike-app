package com.vlad.bikegarage.rides.presentation.addride.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.presentation.list.components.ActionButton
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CustomDatePicker(onDateSelected: (Long) -> Unit = {}, onDismissDialog: () -> Unit = {}) {
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }
    })

    val selectedDate = datePickerState.selectedDateMillis?.let {
        it
    } ?: Long.MAX_VALUE

    DatePickerDialog(
        onDismissRequest = onDismissDialog,
        confirmButton = {
            ActionButton(
                text = stringResource(id = R.string.set_dialog_action),
                onButtonClick = { onDateSelected(selectedDate) },
                modifier = Modifier.padding(8.dp),
            )
        },
        dismissButton = {
            TextButton(
                onClick = { onDismissDialog() },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(stringResource(id = R.string.cancel_dialog_action))
            }
        }) {
        DatePicker(
            state = datePickerState, showModeToggle = false, colors = DatePickerDefaults.colors(
                selectedDayContainerColor = MaterialTheme.colors.primary,
                todayContentColor = MaterialTheme.colors.primary,
                todayDateBorderColor = MaterialTheme.colors.primary,
                selectedYearContainerColor = MaterialTheme.colors.primary,
                selectedYearContentColor = MaterialTheme.colors.onPrimary,
                currentYearContentColor = MaterialTheme.colors.primary,
            )
        )
    }

}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy")
    return formatter.format(Date(millis))
}