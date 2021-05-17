package automated_reasoning

internal fun parseExpressionNormal(tokens: List<String>): Pair<Expression, List<String>> =
    (parseProduct(tokens)).let { (expression, unparsedTokens) ->
        when {
            unparsedTokens.isNotEmpty() && unparsedTokens.first() == "+" -> parseExpressionNormal(unparsedTokens.drop(1)).let { Pair(
                Expression.Add(expression, it.first), it.second) }
            else -> Pair(expression, unparsedTokens)
        }
    }

private fun parseProduct(tokens: List<String>): Pair<Expression, List<String>> =
    parseAtom(tokens).let { (expression, unparsedTokens) ->
        when {
            unparsedTokens.isNotEmpty() && unparsedTokens.first() == "*" -> parseProduct(unparsedTokens.drop(1)).let { Pair(
                Expression.Mul(expression, it.first), it.second) }
            else -> Pair(expression, unparsedTokens)
        }
    }

private fun parseAtom(tokens: List<String>): Pair<Expression, List<String>> {
    if(tokens.isEmpty()) throw MissingTokenException("Expected an expression at end of input")
    val firstToken = tokens.first()
    val remainingTokens = tokens.drop(1)
    return when (firstToken) {
        "(" -> {
            val (expression, unparsedTokens) = parseExpressionNormal(remainingTokens)
            if(unparsedTokens.firstOrNull() != ")") throw MissingClosingBracketException("Expected closing bracket (actual: ${unparsedTokens.firstOrNull()})")
            Pair(expression, unparsedTokens.drop(1).toMutableList())
        }
        else -> {
            Pair(when(firstToken.all(numeric)) {
                true -> Expression.Const(firstToken.toInt())
                else -> Expression.Var(firstToken)
            }, remainingTokens)
        }
    }
}
