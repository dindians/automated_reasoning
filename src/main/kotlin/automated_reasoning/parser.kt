package automated_reasoning

fun makeParser(lex: (String) -> List<String>, parse: (List<String>) -> Pair<Expression, List<String>>) =
    { sequence:String ->
        parse(lex(sequence)).let {
            when {
                it.second.isNotEmpty() -> throw UnparsedParsedInputException("Unparsed input (${it.second.joinToString(",")})")
                else -> it.first
            }
        }
    }

internal class UnparsedParsedInputException(message: String): Throwable(message)

fun defaultParser() = makeParser(::lex, ::parseExpression)
