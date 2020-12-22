import kotlin.random.Random

private val alphanumericChars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
private val spaceChars = listOf(' ', '\t', '\n', '\r')

internal fun randomSpaces(length: Int) = (1..length)
    .map { Random.nextInt(0, spaceChars.size) }
    .map(spaceChars::get)
    .joinToString("")

internal fun randomAlphanumericString(length: Int) = (1..length)
    .map { Random.nextInt(0, alphanumericChars.size) }
    .map(alphanumericChars::get)
    .joinToString("")
