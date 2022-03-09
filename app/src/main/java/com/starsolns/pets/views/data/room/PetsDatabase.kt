package com.starsolns.pets.views.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.starsolns.pets.views.model.Pet

@Database(entities = [Pet::class], version = 1)
abstract class PetsDatabase: RoomDatabase() {

    abstract fun petDao(): PetDao

    companion object{

        @Volatile
        private var INSTANCE: PetsDatabase? = null

        fun getDatabase(context: Context): PetsDatabase{
            var tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PetsDatabase::class.java,
                    "pets_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }

    }
}