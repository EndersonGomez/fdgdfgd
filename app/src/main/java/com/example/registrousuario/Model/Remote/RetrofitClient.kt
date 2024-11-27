package com.example.registrousuario.Model.Remote

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Clase que crea la instancia el objeto Retrofit
class RetrofitClient {

    //Codigo que instancia el objeto Retrofit
    companion object {

        //Variable constante que tiene la URL base.
        private const val BASE_URL =
            "https://script.google.com/macros/s/"

        //Función que ejecuta el código Retrofit
        fun retrofitInstance(): ApiCall {
            /*Se crea un intenceptor de registro para intenceptar las solicitudes y respuestas http
            y saber claramente que esta sucediendo*/
            val loggingInterceptor= HttpLoggingInterceptor{ message ->
                Log.d(
                    "RETROFIT DEPURADOR",
                    message
                )
            }

            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            //Se crea un cliente para agregarle el interceptor.
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            //Se ejecuta y crea la instancia de nuestro retrofit.
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client) //Usar el cliente con el interceptor
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiCall::class.java)
        }
    }
}