package edu.ucne.proyectkeycar.presentation.keyType

import edu.ucne.proyectkeycar.data.entities.KeyTypeEntity
import edu.ucne.proyectkeycar.data.remote.dto.KeyTypeDto

data class Uistate (
    val keyTypeId: Int? = null,
    val tipoLlave: String? = "",
    val errorTipoLlave: String = "",
    val keyTypes: List<KeyTypeEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

fun Uistate.toEntity() = KeyTypeDto(
    keyTypeId = keyTypeId,
    tipoLLave = tipoLlave
)