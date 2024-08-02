package com.compose.mikasa.di

import com.compose.mikasa.api.ApiServices
import com.compose.mikasa.repository.ApiHelper
import com.compose.mikasa.repository.ApiHelperImpl
import com.compose.mikasa.repository.MiKasaRepository
import com.compose.mikasa.utils.Constants.BASE_URL
import com.compose.mikasa.utils.Constants.NETWORK_TIMEOUT
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBase() = BASE_URL

    @Provides
    @Singleton
    fun connectionTimeOut() = NETWORK_TIMEOUT

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @JvmStatic
    @Provides
    @Singleton
    fun provideApiInterface(): ApiServices {
        val retrofit = getRetrofit(okHttpClient)
        return retrofit.create(ApiServices::class.java)
    }

    @JvmStatic
    @get:Singleton
    @get:Provides
    val okHttpClient: OkHttpClient
        get() {
            val okHttpClientBuilder = OkHttpClient.Builder()
            okHttpClientBuilder.addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
            return okHttpClientBuilder.build()
        }

    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient?): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(GsonConverterFactory.create())
            .client(okHttpClient!!)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiHelper(apiServices: ApiServices): ApiHelper {
        return ApiHelperImpl(apiServices)
    }

    @Provides
    @Singleton
    fun provideMiKasaRepository(apiHelper: ApiHelper): MiKasaRepository {
        return MiKasaRepository(apiHelper)

    }
}