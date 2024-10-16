package edu.ucne.proyectkeycar.presentation.keyCar

interface KeyCarEvent {
    data class OnchangeNombre(val nombre: String) : KeyCarEvent
    data class OnchangeCosto(val costo: String) : KeyCarEvent
    data class OnchangePrecio(val precio: String) : KeyCarEvent
    data class OnchangeIva(val iva: String) : KeyCarEvent
    data class OnchangeKeyTypeId(val keyTypeId: Int) : KeyCarEvent
    object Save : KeyCarEvent

}