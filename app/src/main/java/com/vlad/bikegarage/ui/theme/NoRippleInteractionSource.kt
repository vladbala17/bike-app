package com.vlad.bikegarage.ui.theme

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class NoRippleInteractionSource: MutableInteractionSource {
    override val interactions: Flow<Interaction>
        get() = emptyFlow()

    override suspend fun emit(interaction: Interaction) {
    }

    override fun tryEmit(interaction: Interaction): Boolean {
       return true
    }
}