package com.example.dictionaryapp.di

import android.app.Application
import androidx.room.Room
import com.example.dictionaryapp.data.Utils.GsonParser
import com.example.dictionaryapp.data.local.Converter
import com.example.dictionaryapp.data.local.Room.WordInfoDataBase
import com.example.dictionaryapp.data.remote.api.DictionaryApi
import com.example.dictionaryapp.data.repository.WordInfoRepositoryImpl
import com.example.dictionaryapp.domain.repository.WordInfoRepository
import com.example.dictionaryapp.domain.use_case.GetWordInfo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfo {
        return GetWordInfo(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        db: WordInfoDataBase,
        api: DictionaryApi
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDataBase {
        return Room.databaseBuilder(
            app,
            WordInfoDataBase::class.java,
            "word_db"
        ).addTypeConverter(Converter(GsonParser(Gson())))
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
}