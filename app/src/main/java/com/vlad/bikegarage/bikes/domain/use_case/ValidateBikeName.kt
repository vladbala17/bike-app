package com.vlad.bikegarage.bikes.domain.use_case

import com.vlad.bikegarage.R
import com.vlad.bikegarage.util.UiText

class ValidateBikeName {

    operator fun invoke(bikeName: String): ValidationResult {
        if (bikeName.isEmpty()) return ValidationResult(
            successful = false, errorMessage = UiText.StringResource(
                R.string.bike_name_validation_error
            )
        )
        return ValidationResult(successful = true, null)
    }
}