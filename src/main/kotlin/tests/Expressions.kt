package tests

import Expression

internal object Expressions {
    val `0 + 0` = Expression.Add(Expression.Const(0), Expression.Const(0))
    val `0 + 1` = Expression.Add(Expression.Const(0), Expression.Const(1))
    val `1 + 0` = Expression.Add(Expression.Const(1), Expression.Const(0))
    val `1 + 2` = Expression.Add(Expression.Const(1), Expression.Const(2))
    val `x + 0` = Expression.Add(Expression.Var("x"), Expression.Const(0))
    val `0 * 0` = Expression.Mul(Expression.Const(0), Expression.Const(0))
    val `0 * 1` = Expression.Mul(Expression.Const(0), Expression.Const(1))
    val `0 * x` = Expression.Mul(Expression.Const(0), Expression.Var("x"))
    val `1 * 1` = Expression.Mul(Expression.Const(1), Expression.Const(1))
    val `2 * 3` = Expression.Mul(Expression.Const(2), Expression.Const(3))
    val `3 * 2` = Expression.Mul(Expression.Const(3), Expression.Const(2))
    val `2 * x + y` = Expression.Add(Expression.Mul(Expression.Const(2), Expression.Var("x")), Expression.Var("y"))
    val `(0 * x + 1) * 3 + 12` = Expression.Add(Expression.Mul(Expression.Add(Expression.Mul(Expression.Const(0), Expression.Var("x")), Expression.Const(1)), Expression.Const(3)), Expression.Const(12))
}