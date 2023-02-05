package dev.aashishtathod.noteit.core.di

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
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
	
    companion object {
        private const val DEFAULT_CONNECT_TIMEOUT_IN_SEC: Long = 90
        private const val DEFAULT_WRITE_TIMEOUT_IN_SEC: Long = 90
        private const val DEFAULT_READ_TIMEOUT_IN_SEC: Long = 90
    }
	
    @Provides
    @Singleton
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(DEFAULT_READ_TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_CONNECT_TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_WRITE_TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            .addNetworkInterceptor(logging)
            .build()
    }
	
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }
	
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .serializeNulls() // To allow sending null values
            .create()
    }
	
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://run.mocky.io/v3/")
        .build()
}
