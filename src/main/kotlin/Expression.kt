/**
 * declares a recursive datatype to represent the abstract syntax of a simple symbolic expression in a tree structure.
 * The expression is allowed to be built from numeric constants, like 0, 1, and 33 and named variables like x using the
 * operations of addition ('+') and multiplication ('*').
 *
 * a formal expression is connected to its corresponding meaning by an interpretation (a function which maps an expression to a meaning)
 *
 * concrete syntax of a symbolic expression
 * abstract syntax of a symbolic expression
 */
sealed class Expression {
    data class Var(val value: String): Expression()
    data class Const(val value: Int): Expression()
    class Add(val expr1: Expression, val expr2: Expression): Expression()
    class Mul(val expr1: Expression, val expr2: Expression): Expression()
}