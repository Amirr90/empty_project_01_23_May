package com.utils.di


import android.app.Application
import android.content.Context
import androidx.room.Room
import com.cart.CartApi
import com.login.api.LoginApi
import com.products.api.ProductApi
import com.utils.BaseUrls
import com.utils.db.room.database.AppDatabase
import com.utils.interceptor.DummyJsonAuthInterceptor
import com.utils.network.api.NetworkApi
import com.utils.redux.ApplicationState
import com.utils.redux.Store
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
object BaseAppModule {
    @Provides
    @Singleton
    fun provideHttpLogger(dummyJsonInterceptor: DummyJsonAuthInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(dummyJsonInterceptor)
            .build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BaseUrls.BASE_URL_DUMMY_JSON)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }


    @Provides
    @Singleton
    fun provideStore(): Store<ApplicationState> = Store(ApplicationState())

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApiService(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCartApiService(retrofit: Retrofit): CartApi {
        return retrofit.create(CartApi::class.java)
    }


    @Provides
    @Singleton
    fun provideRoomDb(application: Application) = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "com.amir.quran"
    ).build()


    @Provides
    @Singleton
    fun provideRoomDao(appDatabase: AppDatabase) = appDatabase.customerDao()

    @Provides
    @Singleton
    fun provideSharedPrefs(applicationState: Application) =
        applicationState.getSharedPreferences("com.utils", Context.MODE_PRIVATE)


}
