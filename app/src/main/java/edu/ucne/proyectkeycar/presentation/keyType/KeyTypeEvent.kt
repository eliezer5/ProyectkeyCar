package edu.ucne.proyectkeycar.presentation.keyType

sealed interface KeyTypeEvent {
    data class OnchangeTypeKey(val typeKey: String) : KeyTypeEvent
    data object Save : KeyTypeEvent
}