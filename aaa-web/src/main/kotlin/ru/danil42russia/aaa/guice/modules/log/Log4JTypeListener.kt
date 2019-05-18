package ru.danil42russia.aaa.guice.modules.log

import com.google.inject.TypeLiteral
import com.google.inject.spi.TypeEncounter
import com.google.inject.spi.TypeListener
import org.apache.logging.log4j.Logger

class Log4JTypeListener : TypeListener {
    override fun <T : Any> hear(typeLiteral: TypeLiteral<T>, typeEncounter: TypeEncounter<T>) {
        var rawType = typeLiteral.rawType
        while (rawType != null) {
            for (field in rawType.declaredFields) {
                if (field.type == Logger::class.java && field.isAnnotationPresent(InjectLogger::class.java)) {
                    typeEncounter.register(Log4JMembersInjector(field))
                }
            }
            rawType = rawType.superclass
        }
    }
}