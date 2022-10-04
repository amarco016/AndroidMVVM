package es.demteam.peliculasfavoritas.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import es.demteam.peliculasfavoritas.data.database.dao.PeliDao
import es.demteam.peliculasfavoritas.data.database.entities.PeliEntity

@Database(entities = [PeliEntity::class], version = 1)
abstract class PelisFavoritasDatabase : RoomDatabase() {
    abstract fun getPeliDao(): PeliDao
}