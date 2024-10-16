package edu.ucne.proyectkeycar.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "KeyTypes")
data class KeyTypeEntity(
    @PrimaryKey
    val keyTypeId : Int?,
    val tipoLLave : String?
)
