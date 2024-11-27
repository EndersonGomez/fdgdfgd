package com.example.registrousuario.Model.Remote

//Clase que crea el objeto para enviar los datos en el post.
data class UserSend (

    //Ruta del libro de google sheet de destino.
    val spreadsheet_id: String,

    //Nombre de la hoja donde se enviaran los datos.
    val sheet: String,

    //Cuerpo de informacion que se guarda dentro de una lista.
    val rows : List<List<String>>
)