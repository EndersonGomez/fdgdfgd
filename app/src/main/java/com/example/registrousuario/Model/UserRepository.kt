package com.example.registrousuario.Model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.registrousuario.Model.Local.UserDataDao
import com.example.registrousuario.Model.Local.UserEntity
import com.example.registrousuario.Model.Remote.RetrofitClient
import com.example.registrousuario.Model.Remote.UserSend
import retrofit2.Response

//Clase que expone los metodos de los datos al modelView.
class UserRepository (private val userDao: UserDataDao) {

    //Instancia del retrofit.
    private val networkService = RetrofitClient.retrofitInstance()

    //Instanciamos una variable que llama a la funcion que trae la base de datos.
    val userListLiveData: LiveData<List<UserEntity>> = userDao.getAllUser()

    //Funcion suspendida para llamar los datos de la api y guardarlos en la base de datos.
    suspend fun fetchUser(){

        //Variable que ejecuta el retrofit dentro de runcatching para atrapar cualquier excepcion.
        val service = kotlin.runCatching { networkService.fetchUserList() }

        service.onSuccess {

            when( it.code() ) {
                in 200 .. 299 -> it.body()?.let {
                    /*Insertamos los datos que llegan de la api a nuestra base de datos local
                    mediante la funcion mapper*/
                    userDao.insertUser(fromInternetToUserEntity(it))
                    Log.e("EJECUCION BASE DE DATOS", it.toString())
                }
                //En caso que no se ejecute en el log nos deberia salir ayuda para resolver el problema.
                else -> Log.e("LA LLAMADA FALLO", "${it.code()}")
            }
        }
        service.onFailure {
            //Mensaje log que en caso de fallar nos muestra el mensaje del error.
            Log.e("ERROR","${it.message}")
        }
    }

    //Funcion que devuelve una instancia del retrofit con mi metodo post.
    suspend fun sendUser(body: UserSend): Response<Unit>{
        return networkService.postUser(body)
    }
}