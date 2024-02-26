package com.vlad.bikegarage.bikes.domain.use_case

import com.vlad.bikegarage.util.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)