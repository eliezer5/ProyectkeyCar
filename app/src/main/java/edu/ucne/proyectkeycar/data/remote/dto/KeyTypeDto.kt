package edu.ucne.proyectkeycar.data.remote.dto

import edu.ucne.proyectkeycar.data.entities.KeyTypeEntity

data class KeyTypeDto (
    val keyTypeId : Int?,
    val tipoLLave : String?
)

fun KeyTypeDto.toEntity() = KeyTypeEntity(
    keyTypeId = keyTypeId,
    tipoLLave = tipoLLave
)
