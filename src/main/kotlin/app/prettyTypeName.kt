package app

import kotlin.reflect.KClass

internal inline fun <reified T> prettyTypeName() = T::class.java.canonicalName

internal fun Any.prettyTypeName(): String = this::class.prettyTypeName()

private fun <T: Any> KClass<T>.prettyTypeName(): String = qualifiedName?:simpleName?:java.canonicalName?:java.name