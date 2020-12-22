package tests

import simplify
import tests.Expressions.`(0 * x + 1) * 3 + 12`
import tests.Expressions.`0 * 0`
import tests.Expressions.`0 * 1`
import tests.Expressions.`0 * x`
import tests.Expressions.`0 + 0`
import tests.Expressions.`0 + 1`
import tests.Expressions.`1 * 1`
import tests.Expressions.`1 * x`
import tests.Expressions.`1 + 0`
import tests.Expressions.`1 + 2`
import tests.Expressions.`2 * (3 + 4)`
import tests.Expressions.`2 * 3 * 4`
import tests.Expressions.`2 * 3 + 4`
import tests.Expressions.`2 * 3`
import tests.Expressions.`2 * x + y`
import tests.Expressions.`3 * 2`
import tests.Expressions.`x + 0`

internal fun testSimplify() {
    sequenceOf(
        ::`0 + 0`,
        ::`0 + 1`,
        ::`1 + 0`,
        ::`1 + 2`,
        ::`x + 0`,
        ::`0 * 0`,
        ::`0 * 1`,
        ::`0 * x`,
        ::`1 * 1`,
        ::`1 * x`,
        ::`2 * 3`,
        ::`3 * 2`,
        ::`2 * 3 + 4`,
        ::`2 * (3 + 4)`,
        ::`2 * 3 * 4`,
        ::`2 * x + y`,
        ::`(0 * x + 1) * 3 + 12`).forEach {
        println("${it.name} = ${simplify(it.get())}")
    }
}


