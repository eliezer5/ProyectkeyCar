package edu.ucne.proyectkeycar.data.remote

import edu.ucne.proyectkeycar.data.remote.dto.KeyCarDto
import edu.ucne.proyectkeycar.data.remote.dto.KeyTypeDto
import javax.inject.Inject

class KeyCarDataResource @Inject constructor(
    private val keyApi: KeyAPI
) {
    suspend fun getKeyTypes() = keyApi.getKeyTypes()
    suspend fun addKeyType(keyTypeDto: KeyTypeDto) = keyApi.addKeyType(keyTypeDto)

    suspend fun getKeyCars() = keyApi.getKeys()
    suspend fun AddKeyCar(keyCarDto: KeyCarDto) = keyApi.addKeyCar(keyCarDto)
}