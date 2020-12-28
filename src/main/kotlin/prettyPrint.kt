fun prettyPrint(expression: Expression, precedenceLevel: Int = 0): String = when(expression) {
    is Expression.Var -> expression.value
    is Expression.Const -> expression.value.toString()
    is Expression.Add -> "${prettyPrint(expression.expr1, 2)} + ${prettyPrint(expression.expr2, 3)}".let {
        when(precedenceLevel) {
            in 3..Int.MAX_VALUE -> "($it)"
            else -> it
        }
    }
    is Expression.Mul -> "${prettyPrint(expression.expr1, 4)} * ${prettyPrint(expression.expr2, 5)}".let {
        when(precedenceLevel) {
            in 5..Int.MAX_VALUE -> "($it)"
            else -> it
        }
    }
}