internal fun parseExpression(tokens: MutableList<String>): Pair<Expression, MutableList<String>> =
    (parseProduct(tokens)).let { (expression, unparsedTokens) ->
     when {
        unparsedTokens.isNotEmpty() && unparsedTokens.first() == "+" -> parseExpression(unparsedTokens.drop(1).toMutableList()).let { Pair(Expression.Add(expression, it.first), it.second) }
        else -> Pair(expression, unparsedTokens)
    }
}

private fun parseProduct(tokens: MutableList<String>): Pair<Expression, MutableList<String>> =
    parseAtom(tokens).let { (expression, unparsedTokens) ->
    when {
        unparsedTokens.isNotEmpty() && unparsedTokens.first() == "*" -> parseProduct(unparsedTokens.drop(1).toMutableList()).let { Pair(Expression.Mul(expression, it.first), it.second) }
        else -> Pair(expression, unparsedTokens)
    }
}

private fun parseAtom(tokens: MutableList<String>): Pair<Expression, MutableList<String>> {
    if(tokens.isEmpty()) throw MissingTokenException("Expected an expression at end of input")
    val firstToken = tokens.first()
    val remainingTokens = tokens.drop(1).toMutableList()
    return when (firstToken) {
        "(" -> {
            val (expression, unparsedTokens) = parseExpression(remainingTokens)
            if(unparsedTokens.firstOrNull() != ")") throw MissingClosingBracketException("Expected closing bracket (actual: ${unparsedTokens.firstOrNull()})")
            Pair(expression, unparsedTokens.drop(1).toMutableList())
        }
        else -> {
            when(firstToken.all(numeric)) {
                true -> Pair(Expression.Const(firstToken.toInt()), remainingTokens)
                else -> Pair(Expression.Var(firstToken), remainingTokens)
            }
        }
    }
}

internal class MissingTokenException(message: String): Throwable(message)
internal class MissingClosingBracketException(message: String): Throwable(message)