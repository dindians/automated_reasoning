fun makeParser(lex: (String) -> MutableList<String>, parse: (MutableList<String>) -> Pair<Expression, MutableList<String>>) =
    { sequence:String ->
        parse(lex(sequence)).let {
            when {
                it.second.isNotEmpty() -> throw Exception("Unparsed input (${it.second.joinToString(",")})")
                else -> it.first
            }
        }
    }

fun defaultParser() = makeParser(::lex, ::parseExpression)
