package es.demteam.peliculasfavoritas.data.network

import es.demteam.peliculasfavoritas.core.Constantes
import es.demteam.peliculasfavoritas.data.model.PeliModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class PeliService @Inject constructor(
    @Named("Populares") private val retrofitPopulares: Retrofit,
    @Named("PorQuery") private val retrofitPorQuery: Retrofit
) {

    private val api_key = Constantes.API_KEY

    suspend fun obtenerPelisPopularesApi(): MutableList<PeliModel> {
        return withContext(Dispatchers.IO) {
            var response = retrofitPopulares.create(PeliApiClient::class.java)
                .obtenerPelis("popular?api_key=" + api_key + "&language=en-US&page=1")
            response.body()?.pelis ?: mutableListOf()
        }
    }

    suspend fun obtenerPelisPorQueryApi(query: String): MutableList<PeliModel> {
        return withContext(Dispatchers.IO) {
            var response = retrofitPorQuery.create(PeliApiClient::class.java)
                .obtenerPelis("movie?api_key=" + api_key + "&language=en-US&query=" + query + "&page=1")
            response.body()?.pelis ?: mutableListOf()
        }
    }
}