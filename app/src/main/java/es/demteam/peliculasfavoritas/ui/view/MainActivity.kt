package es.demteam.peliculasfavoritas.ui.view

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import es.demteam.peliculasfavoritas.databinding.ActivityMainBinding
import es.demteam.peliculasfavoritas.ui.view.adapter.PagerAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = PagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, index ->
            tab.text = when (index) {
                0 -> {
                    "Populares"
                }
                1 -> {
                    "Favoritas"
                }
                else -> {
                    throw Resources.NotFoundException("No se ha encontrado la posición")
                }
            }
        }.attach()
    }
}