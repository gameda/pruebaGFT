package com.dgm.pruebagft.app

import android.content.Context
import android.content.SharedPreferences
import com.dgm.pruebagft.repository.AppRepository
import com.dgm.pruebagft.repository.local.prefs.AppPreferencesHelper
import com.dgm.pruebagft.repository.remote.api.AppApi
import com.dgm.pruebagft.viewmodel.MainViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val apiModule = module {
    fun provideApi(retrofit: Retrofit) =
        retrofit.create(AppApi::class.java)

    //Manda el single Retrofit Object
    single { provideApi(get()) }
}

val clientModule = module {

    fun interceptor() =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    fun provideHttpClient(interceptor: HttpLoggingInterceptor) =
        OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

    fun provideGson() = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()

    fun provideRetrofit(client: OkHttpClient, gsonFactory: Gson) =
        Retrofit.Builder()
            .baseUrl("https://mighty-refuge-81707.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gsonFactory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()

    single { interceptor() }
    single { provideHttpClient(get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
}


val prefModule = module {
    fun providePrefs(context: Context, prefFileName: String ) =
        context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    fun provideSharePreferences(pref: SharedPreferences)=
        AppPreferencesHelper(pref)

    single { providePrefs(androidApplication(), "myPrefs") }
    single { provideSharePreferences(get())}

}

val repositoryModule = module {
    fun provideRepository(api: AppApi, prefs: AppPreferencesHelper) =
        AppRepository(api, prefs)

    single { provideRepository(get(), get()) }
}

