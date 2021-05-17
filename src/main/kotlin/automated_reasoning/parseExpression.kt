package automated_reasoning

internal fun parseExpression(tokens: List<String>) = when(true) {
    true -> parseExpressionTailrec(tokens)
    false -> parseExpressionNormal(tokens)
}
