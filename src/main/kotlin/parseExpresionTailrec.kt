internal tailrec fun parseExpressionTailrec(tokens: List<String>, accumulateExpression: (Expression) -> Expression = { it }): Pair<Expression, List<String>> {
    val (expression, unparsedTokens) = parseProduct(tokens, accumulateExpression)
    return when {
        unparsedTokens.isEmpty() || unparsedTokens.first() != "+" -> Pair(expression, unparsedTokens)
        else -> parseExpressionTailrec(unparsedTokens.drop(1)) { (Expression.Add(expression, it)) }
    }
}

private tailrec fun parseProduct(tokens: List<String>, accumulateExpression: (Expression) -> Expression): Pair<Expression, List<String>> {
    val (expression, unparsedTokens) = parseAtom(tokens)
    return when {
        unparsedTokens.isEmpty() || unparsedTokens.first() != "*" -> Pair(accumulateExpression(expression), unparsedTokens)
        else -> parseProduct(unparsedTokens.drop(1)) { accumulateExpression(Expression.Mul(expression, it)) }
    }
}

private fun parseAtom(tokens: List<String>): Pair<Expression, List<String>> {
    if(tokens.isEmpty()) throw MissingTokenException("Expected an expression at end of input")
    val firstToken = tokens.first()
    val remainingTokens = tokens.drop(1)
    return when (firstToken) {
        "(" -> {
            val (expression, unparsedTokens) = parseExpressionTailrec(remainingTokens)
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

internal class MissingTokenException(message: String): Throwable(message)
internal class MissingClosingBracketException(message: String): Throwable(message)