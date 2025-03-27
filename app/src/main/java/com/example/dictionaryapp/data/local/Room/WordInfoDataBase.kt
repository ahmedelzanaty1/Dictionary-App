package com.example.dictionaryapp.data.local.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dictionaryapp.data.local.Converter
import com.example.dictionaryapp.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 2
)
@TypeConverters(Converter::class)
abstract class WordInfoDataBase : RoomDatabase() {
    abstract val dao: WordInfoDao

}