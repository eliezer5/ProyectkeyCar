package edu.ucne.proyectkeycar.data.remote

import edu.ucne.proyectkeycar.data.remote.dto.KeyCarDto
import edu.ucne.proyectkeycar.data.remote.dto.KeyTypeDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface KeyAPI {

    @GET("api/Keys")
    suspend fun getKeys(): List<KeyCarDto>

    @POST("api/Keys/")
    suspend fun addKeyCar(@Body keyCarDto: KeyCarDto): KeyCarDto


    @GET("api/KeyTypes")
    suspend fun getKeyTypes(): List<KeyTypeDto>

    @POST("api/KeyTypes/")
    suspend fun addKeyType(@Body keyTypeDto: KeyTypeDto) : KeyTypeDto
}