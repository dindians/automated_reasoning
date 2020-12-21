package tests

import lexRec

internal fun testLexRec() {
    val input = "123   ab{ { cdq   - qwerty } + 8 }"
    println("input: $input")

    with(lexRec(input)) {
        println("found $size tokens")
        forEach { println("[$it]") }
    }
}