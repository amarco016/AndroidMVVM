package es.demteam.peliculasfavoritas.domain

import es.demteam.peliculasfavoritas.data.model.PeliModel
import es.demteam.peliculasfavoritas.data.network.PeliService
import javax.inject.Inject

class ObtenerPeliPorQueryUseCase @Inject constructor(private val peliService: PeliService) {
    suspend fun obtenerPelisPorQuery(query: String): MutableList<PeliModel>? {
        return peliService.obtenerPelisPorQueryApi(query)
    }
}