package edu.ucne.proyectkeycar.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.proyectkeycar.data.entities.KeyTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KeyTypeDao {
    @Upsert
    suspend fun save(keyType: KeyTypeEntity)
    @Query("""
        select * from keytypes where keyTypeId=:id
        limit 1
    """)
    suspend fun find(id: Int): KeyTypeEntity
    @Delete
    suspend fun delete(keyType: KeyTypeEntity)
    @Query("""Select * from KeyTypes""")
    fun getAll(): Flow<List<KeyTypeEntity>>

}