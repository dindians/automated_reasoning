internal fun parseExpression(tokens: MutableList<String>): Pair<Expression, MutableList<String>> {
    val (expression, unparsedTokens) = parseProduct(tokens)
    return when {
        unparsedTokens.isNotEmpty() && unparsedTokens.first() == "+" -> parseExpression(unparsedTokens.drop(1).toMutableList()).let { Pair(Expression.Add(expression, it.first), it.second) }
        else -> Pair(expression, unparsedTokens)
    }
}

private fun parseProduct(tokens: MutableList<String>): Pair<Expression, MutableList<String>> {
    val (expression, unparsedTokens) = parseAtom(tokens)
    return when {
        unparsedTokens.isNotEmpty() && unparsedTokens.first() == "*" -> parseProduct(unparsedTokens.drop(1).toMutableList()).let { Pair(Expression.Mul(expression, it.first), it.second) }
        else -> Pair(expression, unparsedTokens)
    }
}

private fun parseAtom(tokens: MutableList<String>): Pair<Expression, MutableList<String>> {
    if(tokens.isEmpty()) throw Exception("Expected an expression at end of input")
    val firstToken = tokens.first()
    val restTokens = tokens.drop(1).toMutableList()
    return when (firstToken) {
        "(" -> {
            val (expression, unparsedTokens) = parseExpression(restTokens)
            if(unparsedTokens.firstOrNull() != ")") throw Exception("Expected closing bracket (actual: ${unparsedTokens.firstOrNull()})")
            Pair(expression, unparsedTokens.drop(1).toMutableList())
        }
        else -> {
            when(firstToken.all(numeric)) {
                true -> Pair(Expression.Const(firstToken.toInt()), restTokens)
                else -> Pair(Expression.Var(firstToken), restTokens)
            }
        }
    }
}