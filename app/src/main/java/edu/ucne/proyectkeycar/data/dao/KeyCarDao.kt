package edu.ucne.proyectkeycar.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.proyectkeycar.data.entities.KeyCarEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface KeyCarDao {
    @Upsert
    suspend fun save(keyCar: KeyCarEntity)

    @Query("""
        select * from keycars where keyId=:id
        limit 1
    """)
    suspend fun find(id: Int): KeyCarEntity
    @Delete
    suspend fun delete(keyCar: KeyCarEntity)
    @Query("""Select * from KeyCars""")
    fun getAll(): Flow<List<KeyCarEntity>>
}