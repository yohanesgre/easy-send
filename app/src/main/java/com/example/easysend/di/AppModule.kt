package com.example.easysend.di

import android.app.Application
import android.content.Context
import com.example.easysend.features.delivery.data.datasource.OrderDetailRemoteDataSource
import com.example.easysend.features.home.data.datasource.OrderRemoteDataSource
import com.example.easysend.features.home.data.datasource.ProfileRemoteDataSource
import com.example.easysend.features.login.data.datasource.LoginRemoteDataSource
import com.example.easysend.network.api.AuthInterceptor
import com.example.easysend.network.api.AuthRemoteDataSource
import com.example.easysend.network.api.EasySendService
import com.example.easysend.utils.ENDPOINT
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideUserCache(context: Context): UserCache =
        UserCache(context)

    @Singleton
    @Provides
    fun provideEasySendService(
        @AuthAPI okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, EasySendService::class.java)

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(easySendService: EasySendService) =
        AuthRemoteDataSource(
            easySendService
        )

    @Singleton
    @Provides
    fun provideLoginRemoteDataSource(easySendService: EasySendService) =
        LoginRemoteDataSource(
            easySendService
        )

    @Singleton
    @Provides
    fun provideProfileRemoteDataSource(easySendService: EasySendService) =
        ProfileRemoteDataSource(
            easySendService
        )

    @Singleton
    @Provides
    fun provideOrderDetailRemoteDataSource(easySendService: EasySendService) =
        OrderDetailRemoteDataSource(
            easySendService
        )

    @Singleton
    @Provides
    fun provideOrderRemoteDataSource(easySendService: EasySendService) =
        OrderRemoteDataSource(
            easySendService
        )

    @AuthAPI
    @Provides
    fun providePrivateOkHttpClient(
        userCache: UserCache,
        upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder()
            .addInterceptor(AuthInterceptor(userCache))
            .build()
    }
/*
    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)*/
    /*
        @Singleton
        @Provides
        fun providePaketUmrohDao(db: AppDatabase) = db.paketUmrohDao()


        @Singleton
        @Provides
        fun providePromoDao(db: AppDatabase) = db.promoDao()

        @Singleton
        @Provides
        fun provideProfileDao(db: AppDatabase) = db.profileDao()
    */
    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)


    private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideService(okhttpClient: OkHttpClient,
                                   converterFactory: GsonConverterFactory, clazz: Class<T>): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }
}
