package com.intern.evtutors.di

import com.intern.evtutors.BuildConfig
import com.intern.evtutors.data.apis.AppInfoAPI
import com.intern.evtutors.data.apis.LessonAPI

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideLessonApi(@Named("LessonSite") retrofit: Retrofit):LessonAPI {
        return retrofit.create(LessonAPI::class.java)
    }

    @Provides
    @Singleton
    @Named("LessonSite")
    fun provideRetrofitLesson(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ):Retrofit {
        return Retrofit.Builder().addConverterFactory(moshiConverterFactory)
            .baseUrl(BuildConfig.BASE_URL_LESSON_DEV)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideAppInfoApi(@Named("AppInfoSite") retrofit: Retrofit):AppInfoAPI {
        return retrofit.create(AppInfoAPI::class.java)
    }

    @Provides
    @Singleton
    @Named("AppInfoSite")
    fun provideRetrofitAppInfo(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ):Retrofit {
        return Retrofit.Builder().addConverterFactory(moshiConverterFactory)
            .baseUrl(BuildConfig.BASE_URL_AGORA_VIDEO_CALL_DEV)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOKHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.interceptors().add(httpLoggingInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return MoshiConverterFactory.create(moshi)
    }

}