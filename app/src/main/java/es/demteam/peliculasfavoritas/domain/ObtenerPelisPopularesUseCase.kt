package es.demteam.peliculasfavoritas.domain

import es.demteam.peliculasfavoritas.data.model.PeliModel
import es.demteam.peliculasfavoritas.data.network.PeliService
import javax.inject.Inject

class ObtenerPelisPopularesUseCase @Inject constructor(private val peliService: PeliService) {
    suspend fun obtenerPelisPopularesApi(): MutableList<PeliModel> {
        return peliService.obtenerPelisPopularesApi()
    }
}