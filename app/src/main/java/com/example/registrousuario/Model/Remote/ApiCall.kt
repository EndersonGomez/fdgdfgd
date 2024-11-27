package com.example.registrousuario.Model.Remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiCall {

    //Anotacion de retrofit que ejecuta el metodo http.
    @GET("AKfycby-3CS53AflQlCTC4jbPCjnUCy7Z1svbnIdd2yXA_lQXtsjFhEtCbB8i9J5NsKvm4S8Iw/exec?spreadsheetId=1aQD1jNQfX4iDiJxcsiZpaixPg009BUCPaI3hQ6rI5rk&sheet=Personal")
    suspend fun fetchUserList(): Response<UserDataApi>

    //Anotacion de retrofit que ejecuta el post a la direccion url otorgada.
    @POST("AKfycbzauEGtaGOZWxXEBJ_wm6CO8CUtoM1JjfEa4CUe7hWaoTBLDU-4o9LQKsNF3cen22XRAw/exec")
    //Funcion que recibe un objeto y recibe un objeto unit de vuelta para saber que fue exitoso.
    suspend fun postUser(@Body body: UserSend): Response<Unit>

}