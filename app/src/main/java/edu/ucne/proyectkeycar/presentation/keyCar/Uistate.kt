package edu.ucne.proyectkeycar.presentation.keyCar

import edu.ucne.proyectkeycar.data.entities.KeyCarEntity
import edu.ucne.proyectkeycar.data.remote.dto.KeyCarDto

data class Uistate(
    val keyId: Int? = null,
    val nombre: String? = "",
    val keyTypeId: Int? = null,
    val costo: Double? = null,
    val precio: Int? = null,
    val iva: Int? = null,
    val errorPrecio: String = "",
    val errorCosto: String = "",
    val errorNombre: String = "",
    val errorIva: String = "",
    val error: String = "",
    val isLoading: Boolean = false,
    val keyCars: List<KeyCarEntity> = emptyList()
)

fun Uistate.toEntity() = KeyCarDto(
    keyId = keyId,
    nombre = nombre,
    keyTypeId = keyTypeId,
    costo = costo,
    precio = precio,
    iva = iva
)
