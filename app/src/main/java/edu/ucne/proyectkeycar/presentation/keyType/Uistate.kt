package edu.ucne.proyectkeycar.presentation.keyType

import edu.ucne.proyectkeycar.data.remote.dto.KeyTypeDto

data class Uistate (
    val keyTypeId: Int? = null,
    val tipoLlave: String? = "",
    val errorTipoLlave: String? = "",
    val keyTypes: List<KeyTypeDto> = emptyList()
)

fun Uistate.toEntity() = KeyTypeDto(
    keyTypeId = keyTypeId,
    tipoLlave = tipoLlave
)