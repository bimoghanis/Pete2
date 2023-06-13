package com.pt2.leg5.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UlasanEntity::class], version = 1, exportSchema = false)
abstract class UlasanDb : RoomDatabase() {
    abstract fun ulasanDao(): UlasanDao

    companion object {
        @Volatile
        private var INSTANCE: UlasanDb? = null

        fun getDatabase(context: Context): UlasanDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UlasanDb::class.java,
                    "ulasan_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
