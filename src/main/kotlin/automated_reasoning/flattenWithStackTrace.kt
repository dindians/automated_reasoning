package automated_reasoning

// Kotlin 1.4 function Throwable.stackTraceToString() cuts of the stack trace after a certain length
internal fun Throwable.flattenWithStackTrace():String = "$this\n${stackTrace.map { "\n$it" }}" + when(cause) {
    null -> ""
    else -> "\n>>>>>>>> inner exception\n${cause?.flattenWithStackTrace()}"
}
