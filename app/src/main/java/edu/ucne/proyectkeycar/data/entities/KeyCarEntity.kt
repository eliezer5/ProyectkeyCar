package edu.ucne.proyectkeycar.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.ucne.proyectkeycar.data.remote.dto.KeyCarDto

@Entity(tableName = "KeyCars")
data class KeyCarEntity(
    @PrimaryKey
    val keyId: Int?,
    val nombre: String?,
    val keyTypeId: Int?,
    val costo: Double?,
    val precio: Int?,
    val iva: Int?
)


