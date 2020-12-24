fun makeParser(lex: (String) -> List<String>, parse: (List<String>) -> Pair<Expression, List<String>>) =
    { sequence:String ->
        parse(lex(sequence)).let {
            when {
                it.second.isNotEmpty() -> throw Exception("Unparsed input (${it.second.joinToString(",")})")
                else -> it.first
            }
        }
    }

fun defaultParser() = makeParser(::lex, ::parseExpression)
