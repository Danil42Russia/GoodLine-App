package ru.danil42russia.aaa.guice.modules.log

import com.google.inject.BindingAnnotation

@BindingAnnotation
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class InjectLogger
