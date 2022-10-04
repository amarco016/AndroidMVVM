package es.demteam.peliculasfavoritas.data.database.dao

import androidx.room.*
import es.demteam.peliculasfavoritas.data.database.entities.PeliEntity

@Dao
interface PeliDao {

    @Query("SELECT * FROM pelis_favoritas_table")
    suspend fun obtenerFavoritos(): MutableList<PeliEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarPeliAFavoritos(peli: PeliEntity)

    @Delete
    suspend fun elimiarPeliDeFavoritos(peli: PeliEntity)
}