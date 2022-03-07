package com.example.check24app.hilt

import com.example.check24app.common.Constants
import com.example.check24app.data.remote.Check24API
import com.example.check24app.data.repository.ShapesRepositoryImpl
import com.example.check24app.domain.repository.ShapesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                var request = chain.request()
//                var newRequest = request.newBuilder().header("Authorization", AppConstants.API.API_KEY)
                chain.proceed(request)
            }
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideGiphyAPI(okHttpClient: OkHttpClient): Check24API {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(Check24API::class.java)
    }

    @Provides
    fun provideSearchRepository(check24API: Check24API): ShapesRepository {
        return ShapesRepositoryImpl(check24API)
    }


}