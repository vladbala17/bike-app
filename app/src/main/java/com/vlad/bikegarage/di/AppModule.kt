package com.vlad.bikegarage.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.vlad.bikegarage.settings.data.UserPreferences
import com.vlad.bikegarage.settings.domain.Preferences
import com.vlad.bikegarage.settings.domain.use_vase.FilterOutDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        app: Application
    ): SharedPreferences {
        return app.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences {
        return UserPreferences(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideFilterDigitsUseCase(): FilterOutDigits {
        return FilterOutDigits()
    }
}