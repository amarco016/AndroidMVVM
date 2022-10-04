package es.demteam.peliculasfavoritas.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.demteam.peliculasfavoritas.core.Constantes
import es.demteam.peliculasfavoritas.data.database.PelisFavoritasDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PelisFavoritasDatabase::class.java, Constantes.PELI_DATABASE)
            .build()

    @Singleton
    @Provides
    fun providePeliDao(db: PelisFavoritasDatabase) = db.getPeliDao()
}