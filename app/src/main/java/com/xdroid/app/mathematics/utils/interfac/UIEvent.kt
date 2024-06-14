package com.xdroid.app.mathematics.utils.interfac

sealed interface UiEvent {
    object InteractionOne : UiEvent
    data class InteractionTwo(val value: String) : UiEvent
}