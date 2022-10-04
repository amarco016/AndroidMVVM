package es.demteam.peliculasfavoritas.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.demteam.peliculasfavoritas.core.Constantes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("Populares")
    fun provideRetrofitPeliculasPopulares(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL_PELIS_POPULARES)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("PorQuery")
    fun provideRetrofitPeliculasBusquedaPersonalizada(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL_PELIS_BUSQUEDA)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}