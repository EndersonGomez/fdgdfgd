package com.example.registrousuario.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDataDao {

    /*Anotacion de room que ejecuta una query del tipo insertar en la base de datos
    el onConflictStrategy replace funciona en caso que se repitan los datos reemplazara por el nuevo dato*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(usuario: List<UserEntity>)

    //Anotacion de room que ejecuta una query para buscar informacion de la tabla de usuario.
    @Query("SELECT * FROM TABLE_USUARIO")
    //Esqueleto de la funcion que trae un array de la base de datos.
    fun getAllUser(): LiveData<List<UserEntity>>

    /*Anotacion de room que ejecuta una query para buscar determinado en la base de datos
    (FALTA TERMINARLA)
     */
    @Query("SELECT * FROM TABLE_USUARIO WHERE RUT LIKE :RUT")
    //Esqueleto de la funcion que trae un conjunto de datos en concreto.
    fun getUser(RUT: String): LiveData<UserEntity>


}