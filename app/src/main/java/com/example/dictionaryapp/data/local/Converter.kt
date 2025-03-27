package com.example.dictionaryapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.dictionaryapp.data.Utils.JsonParser
import com.example.dictionaryapp.domain.model.Meaning
import com.google.gson.reflect.TypeToken
@ProvidedTypeConverter
class Converter(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun FromMeaningJson (json: String): List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: emptyList()

    }
    @TypeConverter
    fun ToMeaningJson(meanings: List<Meaning>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: "[]"

    }
}