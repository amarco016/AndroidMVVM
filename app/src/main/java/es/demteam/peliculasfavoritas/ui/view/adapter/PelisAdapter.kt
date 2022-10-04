package es.demteam.peliculasfavoritas.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.demteam.peliculasfavoritas.R
import es.demteam.peliculasfavoritas.data.model.PeliModel

class PelisAdapter(
    private val favoritas: Boolean = false,
    private val onClickListener: (PeliModel) -> Unit
) : RecyclerView.Adapter<PelisViewHolder>() {

    private var pelis: List<PeliModel> = emptyList()

    fun RecyclerAdapter(pelis: List<PeliModel>) {
        this.pelis = pelis
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PelisViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PelisViewHolder(layoutInflater.inflate(R.layout.item_lista_pelis, parent, false))
    }

    override fun onBindViewHolder(holder: PelisViewHolder, position: Int) {
        val item = pelis.get(position)
        holder.render(favoritas, item, onClickListener)
    }

    override fun getItemCount(): Int {
        return pelis.size
    }
}