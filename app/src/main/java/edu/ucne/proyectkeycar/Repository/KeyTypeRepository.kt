package edu.ucne.proyectkeycar.Repository

import edu.ucne.proyectkeycar.data.remote.KeyCarDataResource
import edu.ucne.proyectkeycar.data.remote.dto.KeyTypeDto
import edu.ucne.proyectkeycar.utils.Resource
import javax.inject.Inject

class KeyTypeRepository @Inject constructor(
    private val keyCarDataResource: KeyCarDataResource
) {
    suspend fun getKeyTypes() : Resource<List<KeyTypeDto>>{
        val result = try {
            keyCarDataResource.getKeyTypes()
        } catch (e: Exception){
            return  Resource.Error("error")
        }
        return Resource.Success(result)
    }

    suspend fun addKeyType(keyTypeDto: KeyTypeDto): Resource<KeyTypeDto>{
        val result = try {
            keyCarDataResource.addKeyType(keyTypeDto)
        }catch (e: Exception){
            return Resource.Error("Error")
        }
        return Resource.Success(result)
    }

}