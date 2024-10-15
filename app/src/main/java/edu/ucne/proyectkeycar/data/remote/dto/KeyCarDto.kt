package edu.ucne.proyectkeycar.data.remote.dto

data class KeyCarDto(
    val keyId: Int,
    val nombre: String?,
    val costo: Double?,
    val precio : Int,
    val iva: Int
)
