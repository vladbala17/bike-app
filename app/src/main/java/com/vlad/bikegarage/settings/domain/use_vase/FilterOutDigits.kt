package com.vlad.bikegarage.settings.domain.use_vase

class FilterOutDigits {
    operator fun invoke(text: String): String {
        return text.filter { it.isDigit() }
    }
}