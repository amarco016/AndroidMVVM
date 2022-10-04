package es.demteam.peliculasfavoritas.ui.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import es.demteam.peliculasfavoritas.data.model.PeliModel
import es.demteam.peliculasfavoritas.databinding.FragmentPelisPopularesBinding
import es.demteam.peliculasfavoritas.ui.view.adapter.PelisAdapter
import es.demteam.peliculasfavoritas.ui.viewmodel.PeliViewModel

@AndroidEntryPoint
class PelisPopularesFragment : Fragment() {

    private var _binding: FragmentPelisPopularesBinding? = null
    private val binding get() = _binding!!
    private val peliViewModel: PeliViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.N)
    private var adapter: PelisAdapter = PelisAdapter { peli -> onItemSelected(peli) }
    lateinit var recyclerView: RecyclerView
    private var pelisFavoritasLista = mutableListOf<PeliModel>()
    private var pelisPopularesLista = mutableListOf<PeliModel>()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPelisPopularesBinding.inflate(inflater, container, false)

        peliViewModel.obtenerPelisFavoritas()
        peliViewModel.peliFavModel.observe(viewLifecycleOwner, Observer {
            pelisFavoritasLista = it
            for (peli in pelisPopularesLista) {
                peli.favorito = pelisFavoritasLista.find { it.id == peli.id } != null
            }
            adapter.notifyDataSetChanged()
        })

        peliViewModel.obtenerPelisPopulares()
        peliViewModel.peliModel.observe(viewLifecycleOwner, Observer {
            pelisPopularesLista = it
            for (peli in pelisPopularesLista) {
                if (pelisFavoritasLista.contains(peli)) {
                    peli.favorito = true
                }
            }
            montarRecyclerView(it)
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                if (!query.isNullOrBlank()) {
                    peliViewModel.obtenerPeliBusquedaPersonalizada(query)
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.isNullOrBlank()) {
                    peliViewModel.obtenerPelisPopulares()
                }
                return false
            }
        })
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun montarRecyclerView(pelis: List<PeliModel>) {
        recyclerView = binding.listaPelis
        context?.let { adapter.RecyclerAdapter(pelis) }
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun onItemSelected(peli: PeliModel) {
        if (pelisFavoritasLista.find { it.id == peli.id } != null) {
            peliViewModel.eliminarPeliDeFavoritos(peli)
            Toast.makeText(context, "Se ha eliminado de la lista favoritos", Toast.LENGTH_SHORT)
                .show()
        } else {
            peliViewModel.guardarPeliEnFavoritos(peli)
            Toast.makeText(context, "Se ha añadido a la lista favoritos", Toast.LENGTH_SHORT).show()
        }
    }
}