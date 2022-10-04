package es.demteam.peliculasfavoritas.data.network

import es.demteam.peliculasfavoritas.data.model.PelisModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PeliApiClient {
    @GET
    suspend fun obtenerPelis(@Url url: String): Response<PelisModel>
}