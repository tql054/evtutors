package com.intern.evtutors.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppInfoSite(
)

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LessonSite()
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RegisterTeacher(
)@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RegisterStudent(
)
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class FatherOfApps()
