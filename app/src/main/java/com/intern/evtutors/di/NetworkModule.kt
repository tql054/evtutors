package com.intern.evtutors.di

import androidx.navigation.Navigator
import com.intern.evtutors.BuildConfig
import com.intern.evtutors.data.apis.*

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
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideAppInfoApi(@Named("AppInfoSite") retrofit: Retrofit):AppInfoAPI {
        return retrofit.create(AppInfoAPI::class.java)
    }

    @Provides
    fun provideLessonApi(@Named("LessonSite") retrofit: Retrofit):LessonAPI {
        return retrofit.create(LessonAPI::class.java)
    }

    @Provides
    fun provideQuizApi(@Named("LessonSite") retrofit: Retrofit):QuizAPI {
        return retrofit.create(QuizAPI::class.java)
    }

    @Provides
    fun provideQuestionApi(@Named("LessonSite") retrofit: Retrofit):QuestionAPI {
        return retrofit.create(QuestionAPI::class.java)
    }

    @Provides
    fun provideCertificateAPI(@Named("RegisterStudent") retrofit: Retrofit): ProfileAPI{
        return retrofit.create(ProfileAPI::class.java)
    }

    @Provides
    @Singleton
    @Named("RegisterStudent")
    fun provideRegisterStudentRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {

        return Retrofit.Builder().addConverterFactory(moshiConverterFactory)
            .baseUrl(BuildConfig.BASE_URL_LOGIN)
            .client(okHttpClient)
            .build()
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
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES) // read timeout


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