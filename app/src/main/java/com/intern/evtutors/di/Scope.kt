package com.intern.evtutors.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppInfoSite(
)

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LessonSite()