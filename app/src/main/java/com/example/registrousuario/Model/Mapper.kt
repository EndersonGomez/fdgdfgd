package com.example.registrousuario.Model

import com.example.registrousuario.Model.Local.UserEntity
import com.example.registrousuario.Model.Remote.UserDataApi

//Funcion que lleva los datos de la llamada api a la base de datos para guardarlo.
fun fromInternetToUserEntity(userList: UserDataApi): List<UserEntity> {
    return userList.persona.map {
        UserEntity(
            ID = it.ID ?: "",
            Nombre = it.Nombre ?: "",
            RUT = it.RUT ?: "",
            Cargo = it.Cargo ?: "",
            Horario = it.Horario ?: "",
            Foto = it.Foto ?: "",
        )
    }
}