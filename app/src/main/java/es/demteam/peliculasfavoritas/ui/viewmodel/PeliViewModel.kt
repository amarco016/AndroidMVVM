package es.demteam.peliculasfavoritas.ui.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.demteam.peliculasfavoritas.data.model.PeliModel
import es.demteam.peliculasfavoritas.domain.*
import kotlinx.coroutines.launch
import java.util.function.Predicate
import javax.inject.Inject

@HiltViewModel
class PeliViewModel @Inject constructor(
    private val obtenerPelisPopularesUseCase: ObtenerPelisPopularesUseCase,
    private val obtenerPeliPorQueryUseCase: ObtenerPeliPorQueryUseCase,
    private val guardarPeliEnFavoritosUseCase: GuardarPeliEnFavoritosUseCase,
    private val obtenerPelisFavoritasUseCase: ObtenerPelisFavoritasUseCase,
    private val eliminarPeliDeFavoritosUseCase: EliminarPeliDeFavoritosUseCase
) : ViewModel() {

    val peliModel = MutableLiveData<MutableList<PeliModel>>()
    val peliFavModel = MutableLiveData<MutableList<PeliModel>>()
    var peliFavLista = mutableListOf<PeliModel>()

    fun obtenerPelisPopulares() {
        viewModelScope.launch {
            val result = obtenerPelisPopularesUseCase.obtenerPelisPopularesApi()
            if (!result.isNullOrEmpty()) {
                peliModel.postValue(result)
            }
        }
    }

    fun guardarPeliEnFavoritos(peli: PeliModel) {
        viewModelScope.launch {
            guardarPeliEnFavoritosUseCase.guardarPeliEnFavoritos(peli)
            peliFavLista.add(peli)
            peliFavModel.postValue(peliFavLista)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun eliminarPeliDeFavoritos(peli: PeliModel) {
        viewModelScope.launch {
            eliminarPeliDeFavoritosUseCase.eliminarPeliDeFavoritos(peli)
            peliFavLista.removeIf(Predicate { it.id == peli.id })
            peliFavModel.postValue(peliFavLista)
        }
    }

    fun obtenerPeliBusquedaPersonalizada(query: String) {
        viewModelScope.launch {
            val result = obtenerPeliPorQueryUseCase.obtenerPelisPorQuery(query)
            if (!result.isNullOrEmpty()) {
                peliModel.postValue(result!!)
            }
        }
    }

    fun obtenerPelisFavoritas() {
        viewModelScope.launch {
            val result = obtenerPelisFavoritasUseCase.obtenerPelisFavoritas()
            if (!result.isNullOrEmpty()) {
                peliFavLista = result
                peliFavModel.postValue(result!!)
            }
        }
    }
}