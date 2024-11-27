package com.example.registrousuario.Model.Remote

//Clase que se utiliza para crear la arraylist que viene de la api.
data class UserDataApi (

    //Atributos de la clase creada
    val persona: List<Empleado>
)

//Clase que se utiliza para crear los objetos que vienen dentro del array.
data class Empleado(

    //Atributos de la clase creada.
    val ID: String,
    val Nombre: String,
    val RUT: String,
    val Cargo: String,
    val Horario: String,
    val Foto: String
)