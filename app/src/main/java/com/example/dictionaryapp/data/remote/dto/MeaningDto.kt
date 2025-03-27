package com.example.dictionaryapp.data.remote.dto

import com.example.dictionaryapp.domain.model.Meaning

data class MeaningDto(
    val antonyms: List<Any>,
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
    val synonyms: List<String>
){
    fun toMeaning(): Meaning {
        return Meaning(
            definition = definitions.map { it.toDefinition() },
            partOfSpeech = partOfSpeech
        )
    }
}