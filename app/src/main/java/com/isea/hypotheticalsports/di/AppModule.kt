package com.isea.hypotheticalsports.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.isea.hypotheticalsports.BuildConfig
import com.isea.hypotheticalsports.repository.SportsRepository
import com.isea.hypotheticalsports.repository.api.SportsAPI
import com.isea.hypotheticalsports.utils.Constants.BASE_URL
import com.isea.hypotheticalsports.viewmodel.MatchesViewModel
import com.isea.hypotheticalsports.viewmodel.TeamViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(logging)
        }

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    fun provideSportsAPI(retrofit: Retrofit): SportsAPI {
        return retrofit.create(SportsAPI::class.java)
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
    single { provideSportsAPI(get()) }
}

val repositoryModule = module {
    single { SportsRepository(get()) }
}

val viewModelModule = module {

    viewModel { TeamViewModel(get()) }
    viewModel { MatchesViewModel(get()) }

}