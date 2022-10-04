package es.demteam.peliculasfavoritas.data.model

import com.google.gson.annotations.SerializedName

data class PelisModel(@SerializedName("results") val pelis: MutableList<PeliModel>)
