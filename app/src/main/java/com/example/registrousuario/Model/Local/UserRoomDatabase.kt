package com.example.registrousuario.Model.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserRoomDatabase: RoomDatabase () {

    //Funcion que tiene que ser abstracta para llamar al dao.
    abstract fun getUserDao(): UserDataDao

    //Codigo para instanciar la base de datos.
    companion object{

        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        fun getDataBase(context: Context): UserRoomDatabase{
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDatabase::class.java,"user_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}