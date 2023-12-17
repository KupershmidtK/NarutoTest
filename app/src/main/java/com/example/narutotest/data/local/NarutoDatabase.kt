package com.example.narutotest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.narutotest.data.dao.NarutoCharEntity

@Database(entities = [NarutoCharEntity::class], version = 1)
abstract class NarutoDatabase: RoomDatabase() {
    abstract val dao: NarutoDao

    companion object {
        @Volatile
        private var Instance: NarutoDatabase? = null

        fun getDatabase(context: Context): NarutoDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, NarutoDatabase::class.java, "naruto.db    ")
                    .build().also { Instance = it }
            }
        }
    }
}