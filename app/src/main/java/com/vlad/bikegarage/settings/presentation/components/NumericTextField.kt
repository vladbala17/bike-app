package com.vlad.bikegarage.settings.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.vlad.bikegarage.bikes.presentation.addbikes.components.TextTextField
import com.vlad.bikegarage.util.SuffixTransformation

@Composable
fun NumericTextField(
    value: String = "100",
    onServiceReminderAdded: (String) -> Unit,
    defaultSuffix: String = "km",
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
    ) {
        TextTextField(
            text = value,
            placeHolder = value,
            onValueChange = {
                onServiceReminderAdded(it)
            },
            requiresDataValidation = false,
            keyboardType = KeyboardType.Number,
            singleLine = true,
            visualTransformation = SuffixTransformation(defaultSuffix),
        )
    }
}