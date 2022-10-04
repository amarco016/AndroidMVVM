package es.demteam.peliculasfavoritas.data.model

import com.google.gson.annotations.SerializedName
import es.demteam.peliculasfavoritas.data.database.entities.PeliEntity

data class PeliModel(
    @SerializedName("poster_path") val imagen: String,
    @SerializedName("title") val titulo: String,
    @SerializedName("id") val id: Int,
    @SerializedName("favorito") var favorito: Boolean
)

//Creamos una función de extensión para mappear PeliModel y PeliEntity
fun PeliEntity.toDatabase() = PeliModel(titulo = titulo, imagen = imagen, id = id, favorito = false)