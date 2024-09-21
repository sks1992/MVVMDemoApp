package sk.sksv.mvvmdemoapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sk.sksv.mvvmdemoapp.network.ApiServices
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesUrl() = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun providesApiServices(url: String): ApiServices {
        return  Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiServices::class.java)
    }
}