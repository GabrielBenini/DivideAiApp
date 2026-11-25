package com.gabriel.benini.divideaiapp.core.di

import com.gabriel.benini.divideaiapp.core.network.NetworkConstants
import com.gabriel.benini.divideaiapp.feature.expense.data.remote.api.ExpenseApi
import com.gabriel.benini.divideaiapp.feature.group.data.remote.api.GroupApi
import com.gabriel.benini.divideaiapp.feature.settlement.data.remote.api.SettlementApi
import com.gabriel.benini.divideaiapp.feature.user.data.remote.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideUserApi(
        retrofit: Retrofit
    ): UserApi{
        return retrofit.create()
    }

    @Provides
    fun provideGroupApi(
        retrofit: Retrofit
    ): GroupApi{
        return retrofit.create()
    }

    @Provides
    fun provideExpenseApi(
        retrofit: Retrofit
    ): ExpenseApi{
        return retrofit.create()
    }

    @Provides
    fun provideSettlementApi(
        retrofit: Retrofit
    ): SettlementApi {
        return retrofit.create()
    }
}