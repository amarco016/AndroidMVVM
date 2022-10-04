package es.demteam.peliculasfavoritas.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import es.demteam.peliculasfavoritas.data.model.PeliModel
import es.demteam.peliculasfavoritas.domain.*
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PeliViewModelTest {

    @RelaxedMockK
    private lateinit var obtenerPelisPopularesUseCase: ObtenerPelisPopularesUseCase

    @RelaxedMockK
    private lateinit var obtenerPeliPorQueryUseCase: ObtenerPeliPorQueryUseCase

    @RelaxedMockK
    private lateinit var guardarPeliEnFavoritosUseCase: GuardarPeliEnFavoritosUseCase

    @RelaxedMockK
    private lateinit var obtenerPelisFavoritasUseCase: ObtenerPelisFavoritasUseCase

    @RelaxedMockK
    private lateinit var eliminarPeliDeFavoritosUseCase: EliminarPeliDeFavoritosUseCase
    private lateinit var peliViewModel: PeliViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        peliViewModel = PeliViewModel(
            obtenerPelisPopularesUseCase,
            obtenerPeliPorQueryUseCase,
            guardarPeliEnFavoritosUseCase,
            obtenerPelisFavoritasUseCase,
            eliminarPeliDeFavoritosUseCase
        )
        Dispatchers.setMain(Dispatchers.Unconfined)

    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `cuando obtenerPelisPopulares devuelve algo, agregar al livedata`() = runTest {
        //Given
        val pelis = mutableListOf(
            PeliModel("", "Avatar", 8697234, false),
            PeliModel("", "Harry Potter", 9374638, false)
        )
        coEvery { obtenerPelisPopularesUseCase.obtenerPelisPopularesApi() } returns pelis

        //When
        peliViewModel.obtenerPelisPopulares()

        //Then
        assert(peliViewModel.peliModel.value == pelis)
    }

    @Test
    fun `cuando obtenerPelisFavoritas devuelve algo, agregar al livedata`() = runTest {
        //Given
        val pelis = mutableListOf(
            PeliModel("", "Avatar", 8697234, false),
            PeliModel("", "Harry Potter", 9374638, false)
        )
        coEvery { obtenerPelisFavoritasUseCase.obtenerPelisFavoritas() } returns pelis

        //When
        peliViewModel.obtenerPelisFavoritas()

        //Then
        assert(peliViewModel.peliFavModel.value == pelis)
    }

    @Test
    fun `cuando obtenerPeliBusquedaPersonalizada devuelve algo, agregar al livedata`() = runTest {
        //Given
        val pelis = mutableListOf(
            PeliModel("", "El señor de los anillos 1", 8697234, false),
            PeliModel("", "El señor de los anillos 2", 9374638, false)
        )
        coEvery { obtenerPeliPorQueryUseCase.obtenerPelisPorQuery("El señor de los anillos") } returns pelis

        //When
        peliViewModel.obtenerPeliBusquedaPersonalizada("El señor de los anillos")

        //Then
        assert(peliViewModel.peliModel.value == pelis)
    }

    @Test
    fun `cuando se guarda una peli en favoritos, agregar al livedata`() = runTest{
        //Given
        val pelis = mutableListOf(PeliModel("","El señor de los anillos 1", 8697234,false), PeliModel("","El señor de los anillos 2", 9374638,false))
        val peli = PeliModel("","El señor de los anillos 3", 2699034,false)
        var pelisFinal = mutableListOf(PeliModel("","El señor de los anillos 1", 8697234,false), PeliModel("","El señor de los anillos 2", 9374638,false),PeliModel("","El señor de los anillos 3", 2699034,false))
        peliViewModel.peliFavLista = pelis

        //When
        peliViewModel.guardarPeliEnFavoritos(peli)

        //Then
        assert(peliViewModel.peliFavModel.value == pelisFinal)
    }

    @Test
    fun `cuando se elimina una peli de favoritos, eliminarlo del livedata`() = runTest{
        //Given
        val pelis = mutableListOf(PeliModel("","El señor de los anillos 1", 8697234,false), PeliModel("","El señor de los anillos 2", 9374638,false))
        val peli = PeliModel("","El señor de los anillos 2", 9374638,false)
        var pelisFinal = mutableListOf(PeliModel("","El señor de los anillos 1", 8697234,false))
        peliViewModel.peliFavLista = pelis

        //When
        peliViewModel.eliminarPeliDeFavoritos(peli)

        //Then
        assert(peliViewModel.peliFavModel.value == pelisFinal)
    }
}