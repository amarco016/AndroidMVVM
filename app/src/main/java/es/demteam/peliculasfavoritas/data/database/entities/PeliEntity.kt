package es.demteam.peliculasfavoritas.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.demteam.peliculasfavoritas.data.model.PeliModel

@Entity(tableName = "pelis_favoritas_table")
data class PeliEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "titulo") val titulo: String,
    @ColumnInfo(name = "imagen") val imagen: String
)

//Creamos una función de extensión para mappear PeliModel y PeliEntity
fun PeliModel.toDatabase() = PeliEntity(titulo = titulo, imagen = imagen, id = id)