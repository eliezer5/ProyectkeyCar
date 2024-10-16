package edu.ucne.proyectkeycar.data.remote.dto

import edu.ucne.proyectkeycar.data.entities.KeyCarEntity

data class KeyCarDto(
    val keyId: Int?,
    val nombre: String?,
    val costo: Double?,
    val precio : Int?,
    val iva: Int?,
    val keyTypeId: Int?
)

fun KeyCarDto.toEntity() = KeyCarEntity(
    keyId = keyId,
    nombre = nombre,
    keyTypeId = keyTypeId,
    costo = costo,
    precio = precio,
    iva = iva
)
