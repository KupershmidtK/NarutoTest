package com.example.narutotest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.narutotest.data.dao.NarutoCharEntity

@Database(entities = [NarutoCharEntity::class], version = 1)
abstract class NarutoDatabase: RoomDatabase() {
    abstract val dao: NarutoDao
}