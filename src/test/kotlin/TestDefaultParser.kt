import automated_reasoning.Expression
import automated_reasoning.defaultParser
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.random.Random
import org.junit.jupiter.api.Assertions.*

@DisplayName("default parser")
internal class TestDefaultParser {
    @Test
    fun `parse 0`() {
        with(defaultParser()("0")) {
            println(this)
            assertEquals(constant(0), this)
        }
    }

    @Test
    fun `parse 00`() {
        with(defaultParser()("00")) {
            println(this)
            assertEquals(constant(0), this)
        }
    }

    @Test
    fun `parse 01`() {
        with(defaultParser()("01")) {
            println(this)
            assertEquals(constant(1), this)
        }
    }

    @Test
    fun `parse 234`() {
        with(defaultParser()("234")) {
            println(this)
            assertEquals(constant(234), this)
        }
    }

    @Test
    fun `parse x`() {
        with(defaultParser()("x")) {
            println(this)
            assertEquals(variable("x"), this)
        }
    }

    @Test
    fun `parse xyz`() {
        with(defaultParser()("xyz")) {
            println(this)
            assertEquals(variable("xyz"), this)
        }
    }

    @Test
    fun `parse 0x_a'2e`() {
        with(defaultParser()("0x_a'2e")) {
            println(this)
            assertEquals(variable("0x_a'2e"), this)
        }
    }

    @Test
    fun `parse 0 + 0`() {
        with(defaultParser()("0 + 0")) {
            println(this)
            assertTrue(this is Expression.Add)
            with(this as Expression.Add) {
                assertEquals(constant(0), expr1)
                assertEquals(constant(0), expr2)
            }
        }
    }

    @Test
    fun `parse 13 + 27`() {
        with(defaultParser()("13 + 27")) {
            println(this)
            assertTrue(this is Expression.Add)
            with(this as Expression.Add) {
                assertEquals(constant(13), expr1)
                assertEquals(constant(27), expr2)
            }
        }
    }

    @Test
    fun `parse x + 5`() {
        with(defaultParser()("x + 5")) {
            println(this)
            assertTrue(this is Expression.Add)
            with(this as Expression.Add) {
                assertEquals(variable("x"), expr1)
                assertEquals(constant(5), expr2)
            }
        }
    }

    @Test
    fun `parse (x + 5)`() {
        with(defaultParser()("(x + 5)")) {
            println(this)
            assertTrue(this is Expression.Add) { "first expression is add" }
            with(this as Expression.Add) {
                assertEquals(variable("x"), expr1)
                assertEquals(constant(5), expr2)
            }
        }
    }

    @Test
    fun `parse (x + y) + z`() {
        with(defaultParser()("(x + y) + z")) {
            println(this)
            assertTrue(this is Expression.Add) { "first expression is add" }
            with(this as Expression.Add) {
                assertTrue(expr1 is Expression.Add) { "second expression is add" }
                with(expr1 as Expression.Add) {
                    assertEquals(variable("x"), expr1)
                    assertEquals(variable("y"), expr2)
                }
                assertEquals(variable("z"), expr2)
            }
        }
    }

    @Test
    fun `parse x + _y + z_`() {
        with(defaultParser()("x + (y + z)")) {
            println(this)
            assertTrue(this is Expression.Add) { "first expression is add" }
            with(this as Expression.Add) {
                assertEquals(variable("x"), expr1)
                assertTrue(expr2 is Expression.Add) { "second expression is add" }
                with(expr2 as Expression.Add) {
                    assertEquals(variable("y"), expr1)
                    assertEquals(variable("z"), expr2)
                }
            }
        }
    }

    @Test
    fun `parse 2 x (x + 5)`() {
        with(defaultParser()("2 * (x + 5)")) {
            println(this)
            assertTrue(this is Expression.Mul) { "first expression is multiply" }
            with(this as Expression.Mul) {
                assertEquals(constant(2), expr1)
                assertTrue(expr2 is Expression.Add) { "second expression is add" }
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
        with(defaultParser()("$randomVariable + $randomInt")) {
            println(this)
            assertTrue(this is Expression.Add)
            with(this as Expression.Add) {
                assertEquals(variable(randomVariable), expr1)
                assertEquals(constant(randomInt), expr2)
            }
        }
    }

    @Test
    fun `parse random x times random int`() {
        val randomVariable = randomAlphanumericString(5)
        val randomInt = Random.nextInt(0, 4)
        with(defaultParser()("$randomVariable * $randomInt")) {
            println(this)
            assertTrue(this is Expression.Mul)
            with(this as Expression.Mul) {
                assertEquals(variable(randomVariable), expr1)
                assertEquals(constant(randomInt), expr2)
            }
        }
    }

    @Test
    fun `parse 36 + 4 + 73`() {
        with(defaultParser()("36 + 4 + 73")) {
            println(this)
            assertTrue(this is Expression.Add)
            with(this as Expression.Add) {
                assertTrue { expr1 is Expression.Add }
                with(expr1 as Expression.Add) {
                    assertEquals(constant(36), expr1)
                    assertEquals(constant(4), expr2)
                }
                assertEquals(constant(73), expr2)
            }
        }
    }
}
