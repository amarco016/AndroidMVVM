package es.demteam.peliculasfavoritas.ui.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.demteam.peliculasfavoritas.R
import es.demteam.peliculasfavoritas.core.Constantes
import es.demteam.peliculasfavoritas.data.model.PeliModel
import es.demteam.peliculasfavoritas.databinding.ItemListaPelisBinding


class PelisViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemListaPelisBinding.bind(view)

    fun render(favoritas: Boolean = false, peli: PeliModel, onClickListener: (PeliModel) -> Unit) {
        binding.tituloPeli.text = peli.titulo

        var imagenEntera = Constantes.BASE_IMAGEN + peli.imagen

        Glide.with(binding.imagenPeli.context).load(imagenEntera).into(binding.imagenPeli)

        //En la lista de pelis favoritas ocultamos el icono "fav"
        if (favoritas) {
            binding.fav.visibility = View.GONE
        } else {
            if (peli.favorito) {
                binding.fav.setImageResource(R.drawable.fav_icon)
            } else {
                binding.fav.setImageResource(R.drawable.fav_icon_outline)
            }
        }

        binding.fav.setOnClickListener {
            onClickListener(peli)
        }

    }
}