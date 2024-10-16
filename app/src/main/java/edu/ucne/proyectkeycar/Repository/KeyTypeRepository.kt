package edu.ucne.proyectkeycar.Repository

import edu.ucne.proyectkeycar.data.dao.KeyTypeDao
import edu.ucne.proyectkeycar.data.entities.KeyTypeEntity
import edu.ucne.proyectkeycar.data.remote.KeyCarDataResource
import edu.ucne.proyectkeycar.data.remote.dto.KeyTypeDto
import edu.ucne.proyectkeycar.data.remote.dto.toEntity
import edu.ucne.proyectkeycar.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class KeyTypeRepository @Inject constructor(
    private val keyTypeDao: KeyTypeDao,
    private val keyCarDataResource: KeyCarDataResource
) {


    fun getKeyTypes() : Flow<Resource<List<KeyTypeEntity>>> = flow {
        emit(Resource.Loading())
        val localKeyTypes = keyTypeDao.getAll().firstOrNull()
        if (localKeyTypes != null && localKeyTypes.isNotEmpty()) {
            emit(Resource.Success(localKeyTypes))
        }
        try {
            emit(Resource.Loading())
            val keyTypes = keyCarDataResource.getKeyTypes()
            keyTypes.forEach { keyType -> keyTypeDao.save(keyType.toEntity()) }
            val updatedLocalKeyTypes = keyTypeDao.getAll().firstOrNull()

            emit(Resource.Success(updatedLocalKeyTypes ?: emptyList()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }

    }
    fun addKeyType(keyTypeDto: KeyTypeDto): Flow<Resource<KeyTypeEntity>> = flow{
        try {
            emit(Resource.Loading())
            val keyType = keyCarDataResource.addKeyType(keyTypeDto)
            val keyTypeEntity = keyType.toEntity()
            keyTypeDao.save(keyTypeEntity)
            emit(Resource.Success(keyTypeEntity))
        } catch (e: retrofit2.HttpException) {
            emit(Resource.Error(e.message ?: "Error al agregar el KeyType"))
        }

    }

}