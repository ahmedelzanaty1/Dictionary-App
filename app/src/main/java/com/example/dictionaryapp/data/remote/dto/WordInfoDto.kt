package com.example.dictionaryapp.data.remote.dto

import com.example.dictionaryapp.data.local.entity.WordInfoEntity
import com.example.dictionaryapp.domain.model.WordInfo

data class WordInfoDto(
    val license: License,
    val meanings: List<MeaningDto>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
){
    fun ToWordInfo () : WordInfo {
        return WordInfo(
            meanings = meanings.map { it.toMeaning() },
            phonetic = phonetic,
            origin = origin,
            word = word)
    }
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings = meanings.map { it.toMeaning() },
            phonetic = phonetic,
            origin = origin,
            word = word)
        }
}