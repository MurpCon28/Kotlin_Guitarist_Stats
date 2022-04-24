package com.example.guitaristsstats.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

//after you changed something in the entity file you need to update the version number
@Database(entities = [GuitarEntity::class], version = 3, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {

    //? means nullable, so if there is nothing there it will still fetch it and not crash
    abstract fun guitarDao(): GuitarDao?

    companion object{
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if(INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "guitaristinfo.db"
                    )
                            //used for updating the database if you changed the enitiy file
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}