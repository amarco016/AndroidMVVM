package es.demteam.peliculasfavoritas.ui.view.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import es.demteam.peliculasfavoritas.ui.view.PelisFavoritasFragment
import es.demteam.peliculasfavoritas.ui.view.PelisPopularesFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                PelisPopularesFragment()
            }
            1 -> {
                PelisFavoritasFragment()
            }
            else -> {
                throw Resources.NotFoundException("No se ha encontrado la posición")
            }
        }
    }
}