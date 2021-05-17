package automated_reasoning

/**
 * automated_reasoning.symbolic computation: apply specified transformation rules to automated_reasoning.simplify an expression
 *  since expressions are recursive, transformation rules are applied repeatedly until no further progress is possible
 *  Simple recursive bottom-up sweep that will automated_reasoning.simplify in a cascaded manner
 */
fun simplify(expr: Expression): Expression = with(expr) {
    when(this) {
        is Expression.Add -> simplify1(Expression.Add(simplify(expr1), simplify(expr2)))
        is Expression.Mul -> simplify1(Expression.Mul(simplify(expr1), simplify(expr2)))
        else -> simplify1(this)
    }
}

/**
 * defines a transformation function via pattern-matching and recursion
 * transformation rules are based on the (mathematical) properties of the operands of the expression
 * four mathematical properties that involve addition:
 * - cumulative: a + b = b + a
 * - associative: (a + b) + c = a + (b + c)
 * - distributive: a * (b + c) = a * c + b * c
 * - additive identity: a + 0 = a
 *
 * todo list the mathematical properties for multiplication
 *
 * a transformation rule is expressed by a starting and a finishing pattern
 */
private fun simplify1(expr: Expression) = with(expr) {
    when {
        // Const(a) + Const(b) = Const(a + b)
        this is Expression.Add && expr1 is Expression.Const && expr2 is Expression.Const -> Expression.Const(expr1.value + expr2.value)
        // Const(a) * Const(b) = Const(a * b)
        this is Expression.Mul && expr1 is Expression.Const && expr2 is Expression.Const -> Expression.Const(expr1.value * expr2.value)
        // Const(0) + a = a
        this is Expression.Add && expr1 is Expression.Const && expr1.value == 0 -> expr2
        // a + Const(0) = a
        this is Expression.Add && expr2 is Expression.Const && expr2.value == 0 -> expr1
        // Const(0) * a = Const(0)
        this is Expression.Mul && expr1 is Expression.Const && expr1.value == 0 -> Expression.Const(0)
        // a * Const(0) = Const(0)
        this is Expression.Mul && expr2 is Expression.Const && expr2.value == 0 -> Expression.Const(0)
        // Const(1) * a = a
        this is Expression.Mul && expr1 is Expression.Const && expr1.value == 1 -> expr2
        // a * Const(1) = a
        this is Expression.Mul && expr2 is Expression.Const && expr2.value == 1 -> expr1
        else -> expr
    }
}

