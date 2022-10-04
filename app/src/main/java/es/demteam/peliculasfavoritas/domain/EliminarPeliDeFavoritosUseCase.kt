package es.demteam.peliculasfavoritas.domain

import es.demteam.peliculasfavoritas.data.database.dao.PeliDao
import es.demteam.peliculasfavoritas.data.database.entities.toDatabase
import es.demteam.peliculasfavoritas.data.model.PeliModel
import javax.inject.Inject

class EliminarPeliDeFavoritosUseCase @Inject constructor(
    private val peliDao: PeliDao
) {
    suspend fun eliminarPeliDeFavoritos(peli: PeliModel) {
        peliDao.elimiarPeliDeFavoritos(peli.toDatabase())
    }
}