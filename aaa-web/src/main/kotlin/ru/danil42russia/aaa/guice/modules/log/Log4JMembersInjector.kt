package ru.danil42russia.aaa.guice.modules.log

import com.google.inject.MembersInjector
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.lang.reflect.Field

class Log4JMembersInjector<T> internal constructor(private val field: Field) : MembersInjector<T> {
    private val logger: Logger = LogManager.getLogger(field.declaringClass.name)

    init {
        field.isAccessible = true
    }

    override fun injectMembers(t: T) {
        try {
            field.set(t, logger)
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        }

    }
}