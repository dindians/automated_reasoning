package tests

import lex

internal fun testLexRec() {
    val input = "123   ab{ { cdq   - qwerty } + 8 }"
    println("input: $input")

    with(lex(input)) {
        println("found $size tokens")
        forEach { println("[$it]") }
    }
}