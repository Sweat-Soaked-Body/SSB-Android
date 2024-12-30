package com.sweat.network.di

import android.content.Context
import android.util.Log
import com.sweat.network.BuildConfig
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.sweat.network.api.AuthApi
import com.sweat.network.api.ExerciseApi
import com.sweat.network.api.FriendApi
import com.sweat.network.api.ProfileApi
import com.sweat.network.util.AuthInterceptor
import com.sweat.network.util.SimpleCookieJar
import com.sweat.network.util.TokenAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message -> Log.v("HTTP", message) }
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        tokenAuthenticator: TokenAuthenticator,
        cookieJar: CookieJar
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .authenticator(tokenAuthenticator)
            .addInterceptor(authInterceptor)
            .addInterceptor(
                ChuckInterceptor(context)
                    .showNotification(true)
                    .maxContentLength(250)
                    .retainDataFor(ChuckInterceptor.Period.ONE_HOUR)
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideCookieJar(): CookieJar {
        return SimpleCookieJar() // 쿠키 저장소
    }

    @Provides
    @Singleton
    fun provideMoshiInstance(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL) // todo : Add BaseUrl - Use BuildConfig
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    // todo : Add API
    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)

    @Provides
    fun provideFriendApi(retrofit: Retrofit): FriendApi =
        retrofit.create(FriendApi::class.java)

    @Provides
    fun provideProfileApi(retrofit: Retrofit): ProfileApi =
        retrofit.create(ProfileApi::class.java)

    @Provides
    fun provideExerciseApi(retrofit: Retrofit): ExerciseApi =
        retrofit.create(ExerciseApi::class.java)
}