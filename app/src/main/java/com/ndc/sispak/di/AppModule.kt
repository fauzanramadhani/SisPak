package com.ndc.sispak.di

import android.content.Context
import com.ndc.sispak.data.local.shared_pref.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideSharedPref(
        @ApplicationContext context: Context,
    ) = SharedPreferencesManager(context)
}