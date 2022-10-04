package es.demteam.peliculasfavoritas.domain

import es.demteam.peliculasfavoritas.data.database.dao.PeliDao
import es.demteam.peliculasfavoritas.data.model.PeliModel
import es.demteam.peliculasfavoritas.data.model.toDatabase
import javax.inject.Inject

class ObtenerPelisFavoritasUseCase @Inject constructor(
    private val peliDao: PeliDao
) {
    suspend fun obtenerPelisFavoritas(): MutableList<PeliModel>? {
        return peliDao.obtenerFavoritos().map { it.toDatabase() } as MutableList<PeliModel>
    }
}