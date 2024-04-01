package com.vlad.bikegarage.bikes.domain.use_case

import com.vlad.bikegarage.R
import com.vlad.bikegarage.util.UiText
import javax.inject.Inject

class ValidateDistance @Inject constructor() {
    operator fun invoke(distance: String): ValidationResult {
        return if (distance.isEmpty()) {
            ValidationResult(false, UiText.StringResource(
                R.string.bike_name_validation_error
            ))
        } else {
            ValidationResult(true, null)
        }
    }
}