package edu.ucne.proyectkeycar.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.proyectkeycar.data.dao.KeyCarDao
import edu.ucne.proyectkeycar.data.dao.KeyTypeDao
import edu.ucne.proyectkeycar.data.entities.KeyCarEntity
import edu.ucne.proyectkeycar.data.entities.KeyTypeEntity

@Database(
    entities = [
        KeyCarEntity::class,
        KeyTypeEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class KeyCarDb : RoomDatabase(){
    abstract fun KeyCarDao(): KeyCarDao
    abstract fun KeyTypeDao(): KeyTypeDao

}