package com.example.registrousuario.ModelView

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.registrousuario.Model.Local.UserEntity
import com.example.registrousuario.Model.Local.UserRoomDatabase
import com.example.registrousuario.Model.Remote.UserSend
import com.example.registrousuario.Model.UserRepository
import kotlinx.coroutines.launch
import okio.IOException

class UserViewModel(application: Application): AndroidViewModel(application) {

    //Instancia del repositorio
    private val repository: UserRepository

    //Funcion vacia para inicializar el viewmodel.
    fun inicializandoViewmodel(){

    }

    //Inicializacion del codigo apenas se instancie la clase.
    init {
        //Instanciamos la base de datos y le pasamos el contexto.
        val baseDato = UserRoomDatabase.getDataBase(application)

        //Intanciamos al dao.
        val userDao = baseDato.getUserDao()

        //Instanciamos el repositorio y le pasamos el contexto.
        repository =  UserRepository(userDao)

        //Llama al metodo del repositorio para matar la courutina y ejecutar el retrofit.
        viewModelScope.launch {
            repository.fetchUser()
        }
    }

    //Funcion que trae todos los usuarios de la base de datos.
    fun getUserList(): LiveData<List<UserEntity>> = repository.userListLiveData

    //Funcion que envia los datos
    fun enviarDatosAlServidor(body: UserSend){
        //Log para saber el formato del json a enviar.
        Log.d("JSON","JSON a enviar: $body")

        viewModelScope.launch {
            try {
                val response = repository.sendUser(body)
                if(response.isSuccessful){
                    //Obtener el contenido de la respuesta.
                    val responseBodyString = response.body()
                    Log.d("TAG", "Respuesta exitosa: $responseBodyString")
                } else {
                    //La llamada fue exitosa, pero la respuesta indica un error.
                    Log.e("TAG", "Error en la respuesta: ${response.code()}")
                    Log.e("TAG", "Respuesta del servidor: ${response.errorBody()?.string()}")
                }
            } catch ( e: IOException){
                Log.e("TAG", "Error al procesar la respuesta: ${e.message}")
            }
        }
    }
}