package com.intern.evtutors.di

import com.intern.evtutors.BuildConfig
import com.intern.evtutors.data.apis.AppInfoAPI
import com.intern.evtutors.data.apis.LessonAPI
import com.intern.evtutors.data.apis.LoginAPI
import com.intern.evtutors.data.apis.RegisterStudentAPI
import com.intern.evtutors.data.apis.RegisterTeacherAPI

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
//    @Provides
//    fun provideCustomerAPI(@Named("MainSite") retrofit: Retrofit): CustomerAPI {
//        return retrofit.create(CustomerAPI::class.java)
 //   }
    @Provides
    fun provideLoginAPI(@Named("Login") retrofit: Retrofit): LoginAPI {
        return retrofit.create(LoginAPI::class.java)
    }
    @Provides
    fun provideRegisterTeacherAPI(@Named("RegisterTeacher") retrofit: Retrofit): RegisterTeacherAPI {
        return retrofit.create(RegisterTeacherAPI::class.java)
    }
    @Provides
    fun provideRegisterStudentAPI(@Named("RegisterStudent") retrofit: Retrofit): RegisterStudentAPI {
        return retrofit.create(RegisterStudentAPI::class.java)
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
    @Named("Login")
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ):Retrofit {
        return Retrofit.Builder().addConverterFactory(moshiConverterFactory)
            .baseUrl(BuildConfig.BASE_URL_LOGIN)
            .client(okHttpClient)
            .build()
    }
    
    @Provides
    @Singleton
    @Named("RegisterTeacher")
    fun provideRegisterTeacherRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {

        return Retrofit.Builder().addConverterFactory(moshiConverterFactory)
            .baseUrl(BuildConfig.BASE_URL_LOGIN)
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