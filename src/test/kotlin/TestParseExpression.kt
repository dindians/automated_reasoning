import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

@DisplayName("test parser")
internal class TestParseExpression {
    @Test
    fun `parse 0`() {
        with(parseExpression(listOf("0"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertEquals(constant(0), this.first)
        }
    }

    @Test
    fun `parse 00`() {
        with(parseExpression(listOf("00"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertEquals(constant(0), this.first)
        }
    }

    @Test
    fun `parse 01`() {
        with(parseExpression(listOf("01"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertEquals(constant(1), this.first)
        }
    }

    @Test
    fun `parse 234`() {
        with(parseExpression(listOf("234"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertEquals(constant(234), this.first)
        }
    }

    @Test
    fun `parse x`() {
        with(parseExpression(listOf("x"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertEquals(variable("x"), this.first)
        }
    }

    @Test
    fun `parse xyz`() {
        with(parseExpression(listOf("xyz"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertEquals(variable("xyz"), this.first)
        }
    }

    @Test
    fun `parse _0)`() {
        with(parseExpression(listOf("(", "0", ")"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertEquals(constant(0), this.first)
        }
    }

    @Test
    fun `parse (0`() {
        assertFailsWith<MissingClosingBracketException> { parseExpression(listOf("(", "0")) }
    }

    @Test
    fun `parse 0 +`() {
        assertFailsWith<MissingTokenException> { parseExpression(listOf("0", "+")) }
    }

    @Test
    fun `parse 0 + + 0`() {
//        assertFailsWith<MissingTokenException> { parseExpression(listOf("0", "+", "+", "0")) }
    }

    @Test
    fun `parse __0))`() {
        with(parseExpression(listOf("(", "(", "0", ")", ")"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertEquals(constant(0), this.first)
        }
    }

    @Test
    fun `parse 0x_a'2e`() {
        with(parseExpression(listOf("0x_a'2e"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertEquals(variable("0x_a'2e"), this.first)
        }
    }

    @Test
    fun `parse 0 + 0`() {
        with(parseExpression(listOf("0", "+", "0"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertTrue { first is Expression.Add }
            with(first as Expression.Add) {
                assertEquals(constant(0), expr1)
                assertEquals(constant(0), expr2)
            }
        }
    }

    @Test
    fun `parse 13 + 27`() {
        with(parseExpression(listOf("13", "+", "27"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertTrue { first is Expression.Add }
            with(first as Expression.Add) {
                assertEquals(constant(13), expr1)
                assertEquals(constant(27), expr2)
            }
        }
    }

    @Test
    fun `parse _13 + 27)`() {
        with(parseExpression(listOf("(", "13", "+", "27", ")"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertTrue { first is Expression.Add }
            with(first as Expression.Add) {
                assertEquals(constant(13), expr1)
                assertEquals(constant(27), expr2)
            }
        }
    }

    @Test
    fun `parse _13) + _27)`() {
        with(parseExpression(listOf("(", "13", ")", "+", "(", "27", ")"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertTrue { first is Expression.Add }
            with(first as Expression.Add) {
                assertEquals(constant(13), expr1)
                assertEquals(constant(27), expr2)
            }
        }
    }

    @Test
    fun `parse __13) + _27))`() {
        with(parseExpression(listOf("(", "(", "13", ")", "+", "(", "27", ")", ")"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertTrue { first is Expression.Add }
            with(first as Expression.Add) {
                assertEquals(constant(13), expr1)
                assertEquals(constant(27), expr2)
            }
        }
    }

    @Test
    fun `parse x + 5`() {
        with(parseExpression(listOf("x", "+", "5"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertTrue { first is Expression.Add }
            with(first as Expression.Add) {
                assertEquals(variable("x"), expr1)
                assertEquals(constant(5), expr2)
            }
        }
    }

    @Test
    fun `parse _x + 5)`() {
        with(parseExpression(listOf("(", "x", "+", "5", ")"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertTrue("first expression is add") { first is Expression.Add }
            with(first as Expression.Add) {
                assertEquals(variable("x"), expr1)
                assertEquals(constant(5), expr2)
            }
        }
    }

    @Test
    fun `parse x + y + z`() {
        with(parseExpression(listOf("x", "+", "y", "+", "z"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertTrue("first expression is add") { first is Expression.Add }
            with(first as Expression.Add) {
                assertTrue("second expression is add") { expr1 is Expression.Add }
                with(expr1 as Expression.Add) {
                    assertEquals(variable("x"), expr1)
                    assertEquals(variable("y"), expr2)
                }
                assertEquals(variable("z"), expr2)
            }
        }
    }

    @Test
    fun `parse (x + y) + z`() {
        with(parseExpression(listOf("(", "x", "+", "y", ")", "+", "z"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertTrue("first expression is add") { first is Expression.Add }
            with(first as Expression.Add) {
                assertTrue("second expression is add") { expr1 is Expression.Add }
                with(expr1 as Expression.Add) {
                    assertEquals(variable("x"), expr1)
                    assertEquals(variable("y"), expr2)
                }
                assertEquals(variable("z"), expr2)
            }
        }
    }

    @Test
    fun `parse x + _y + z)`() {
        with(parseExpression(listOf("x", "+", "(", "y", "+", "z", ")"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertTrue("first expression is add") { first is Expression.Add }
            with(first as Expression.Add) {
                assertEquals(variable("x"), expr1)
                assertTrue("second expression is add") { expr2 is Expression.Add }
                with(expr2 as Expression.Add) {
                    assertEquals(variable("y"), expr1)
                    assertEquals(variable("z"), expr2)
                }
            }
        }
    }

    @Test
    fun `parse 2 times _x + 5)`() {
        with(parseExpression(listOf("2", "*", "(", "x", "+", "5", ")"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertTrue("first expression is multiply") { first is Expression.Mul }
            with(first as Expression.Mul) {
                assertEquals(constant(2), expr1)
                assertTrue("second expression is add") { expr2 is Expression.Add }
                with(expr2 as Expression.Add) {
                    assertEquals(variable("x"), expr1)
                    assertEquals(constant(5), expr2)
                }
            }
        }
    }

    @Test
    fun `parse random variable + random int`() {
        val randomVariable = randomAlphanumericString(5)
        val randomInt = Random.nextInt(0, 4)
        with(parseExpression(listOf(randomVariable, "+", randomInt.toString()))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertTrue { first is Expression.Add }
            with(first as Expression.Add) {
                assertEquals(variable(randomVariable), expr1)
                assertEquals(constant(randomInt), expr2)
            }
        }
    }

    @Test
    fun `parse random x times random int`() {
        val randomVariable = randomAlphanumericString(5)
        val randomInt = Random.nextInt(0, 4)
        with(parseExpression(listOf(randomVariable, "*", randomInt.toString()))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertTrue { first is Expression.Mul }
            with(first as Expression.Mul) {
                assertEquals(variable(randomVariable), expr1)
                assertEquals(constant(randomInt), expr2)
            }
        }
    }

    @Test
    fun `parse 36 + 4 + 73`() {
        with(parseExpression(listOf("36", "+", "4", "+", "73"))) {
            println(this)
            assertTrue { first is Expression.Add }
            with(first as Expression.Add) {
                assertTrue("remaining tokens is empty") { second.isEmpty() }
                assertTrue { expr1 is Expression.Add }
                with(expr1 as Expression.Add) {
                    assertEquals(constant(36), expr1)
                    assertEquals(constant(4), expr2)
                }
                assertEquals(constant(73), expr2)
            }
        }
    }

    @Test
    fun `parse 36 times 4 times 73`() {
        with(parseExpression(listOf("36", "*", "4", "*", "73"))) {
            println(this)
            assertTrue("remaining tokens is empty") { second.isEmpty() }
            assertTrue { first is Expression.Mul }
            with(first as Expression.Mul) {
                assertEquals(constant(36), expr1)
                assertTrue { expr2 is Expression.Mul }
                with(expr2 as Expression.Mul) {
                    assertEquals(constant(4), expr1)
                    assertEquals(constant(73), expr2)
                }
            }
        }
    }
}