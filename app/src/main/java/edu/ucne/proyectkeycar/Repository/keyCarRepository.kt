package edu.ucne.proyectkeycar.Repository

import android.net.http.HttpException
import android.text.Html
import android.util.Log
import edu.ucne.proyectkeycar.data.dao.KeyCarDao
import edu.ucne.proyectkeycar.data.entities.KeyCarEntity
import edu.ucne.proyectkeycar.data.remote.KeyCarDataResource
import edu.ucne.proyectkeycar.data.remote.dto.KeyCarDto
import edu.ucne.proyectkeycar.data.remote.dto.toEntity
import edu.ucne.proyectkeycar.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import retrofit2.http.HTTP
import javax.inject.Inject

class keyCarRepository @Inject constructor(
    private val keyCarDao: KeyCarDao,
    private val keyCarDataResource: KeyCarDataResource
) {

    fun getKeyCars(): Flow<Resource<List<KeyCarEntity>>> = flow {
        emit(Resource.Loading())

        val localKeyCars = keyCarDao.getAll().firstOrNull()
        if (localKeyCars != null && localKeyCars.isNotEmpty()) {
            emit(Resource.Success(localKeyCars))
        }
        try {
            emit(Resource.Loading())
            val keyCars = keyCarDataResource.getKeyCars()
            keyCars.forEach { keyCar -> keyCarDao.save(keyCar.toEntity()) }
            val updatedLocalKeyCars = keyCarDao.getAll().firstOrNull()
            emit(Resource.Success(updatedLocalKeyCars ?: emptyList()))

        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }


    }



    fun AddKeyCar(keyCarDto: KeyCarDto): Flow<Resource<KeyCarEntity>> = flow {
        try {
            emit(Resource.Loading())
            val keyCar = keyCarDataResource.AddKeyCar(keyCarDto)
            val keyCarEntity = keyCar.toEntity()
            keyCarDao.save(keyCarEntity)
            emit(Resource.Success(keyCarEntity))


        } catch (e: retrofit2.HttpException) {
            emit(Resource.Error(e.message ?: "Error al agregar el KeyCar"))
        }
    }
}
