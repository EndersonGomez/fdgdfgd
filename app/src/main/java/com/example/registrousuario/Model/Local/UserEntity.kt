package com.example.registrousuario.Model.Local

import androidx.room.Entity
import androidx.room.PrimaryKey

//Clase que representa una tabla dentro de nuestra base de datos.
@Entity(tableName = "TABLE_USUARIO")
data class UserEntity (

    val ID: String,
    val Nombre: String,
    @PrimaryKey
    val RUT: String,
    val Cargo: String,
    val Horario: String,
    val Foto: String

)