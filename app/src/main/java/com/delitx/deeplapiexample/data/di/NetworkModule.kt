package com.delitx.deeplapiexample.data.di

import com.delitx.deeplapiexample.data.network.DeeplAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder()
                .header("Authorization", "DeepL-Auth-Key YOUR_API_KEY")
                .method(original.method(), original.body())
                .build()

            chain.proceed(request)
        }
        val client = httpClient.build()
        return Retrofit.Builder()
            .baseUrl("https://api-free.deepl.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideDeeplAPIService(retrofit: Retrofit): DeeplAPIService {
        return retrofit.create(DeeplAPIService::class.java)
    }
}