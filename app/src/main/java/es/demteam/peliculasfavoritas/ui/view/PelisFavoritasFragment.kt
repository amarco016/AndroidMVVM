package es.demteam.peliculasfavoritas.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import es.demteam.peliculasfavoritas.data.model.PeliModel
import es.demteam.peliculasfavoritas.databinding.FragmentPelisFavoritasBinding
import es.demteam.peliculasfavoritas.ui.view.adapter.PelisAdapter
import es.demteam.peliculasfavoritas.ui.viewmodel.PeliViewModel

@AndroidEntryPoint
class PelisFavoritasFragment : Fragment() {

    private var _binding: FragmentPelisFavoritasBinding? = null
    private val binding get() = _binding!!
    private val peliViewModel: PeliViewModel by activityViewModels()

    //Para indicar al adapter que estamos en la lista de favs
    private val esListaFav = true
    private var adapter: PelisAdapter = PelisAdapter(esListaFav) { peli -> onItemSelected(peli) }
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPelisFavoritasBinding.inflate(inflater, container, false)
        peliViewModel.obtenerPelisFavoritas()
        peliViewModel.peliFavModel.observe(viewLifecycleOwner, Observer {
            montarRecyclerView(it)
        })

        return binding.root
    }

    private fun montarRecyclerView(pelis: List<PeliModel>) {
        recyclerView = binding.listaPelisFav
        context?.let { adapter.RecyclerAdapter(pelis) }
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
    }

    fun onItemSelected(peli: PeliModel) {
    }

    override fun onResume() {
        super.onResume()
        peliViewModel.obtenerPelisFavoritas()
    }
}