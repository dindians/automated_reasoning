import automated_reasoning.simplify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("test automated_reasoning.simplify")
internal class TestSimplify {
    @Test
    fun `simplify 0 + 0`() {
        with(simplify(add(constant(0), constant(0)))) {
            println(this)
            assertEquals(constant(0), this)
        }
    }

    @Test
    fun `simplify 0 + 1`() {
        with(simplify(add(constant(0), constant(1)))) {
            println(this)
            assertEquals(constant(1), this)
        }
    }

    @Test
    fun `simplify 1 + 0`() {
        with(simplify(add(constant(1), constant(0)))) {
            println(this)
            assertEquals(constant(1), this)
        }
    }

    @Test
    fun `simplify 1 + 2`() {
        with(simplify(add(constant(1), constant(2)))) {
            println(this)
            assertEquals(constant(3), this)
        }
    }
    @Test
    fun `simplify x + 0`() {
        with(simplify(add(variable("x"), constant(0)))) {
            println(this)
            assertEquals(variable("x"), this)
        }
    }

    @Test
    fun `simplify (2 + 3) + 4`() {
        with(simplify(add(add(constant(2), constant(3)), constant(4)))) {
            println(this)
            assertEquals(constant(9), this)
        }
    }

    @Test
    fun `simplify 2 + (3 + 4)`() {
        with(simplify(add(constant(2), add(constant(3), constant(4))))) {
            println(this)
            assertEquals(constant(9), this)
        }
    }

    @Test
    fun `simplify 0 * 0`() {
        with(simplify(multiply(constant(0), constant(0)))) {
            println(this)
            assertEquals(constant(0), this)
        }
    }

    @Test
    fun `simplify 0 * 1`() {
        with(simplify(multiply(constant(0), constant(1)))) {
            println(this)
            assertEquals(constant(0), this)
        }
    }

    @Test
    fun `simplify 0 * x`() {
        with(simplify(multiply(constant(0), variable("x")))) {
            println(this)
            assertEquals(constant(0), this)
        }
    }

    @Test
    fun `simplify 1 * 1`() {
        with(simplify(multiply(constant(1), constant(1)))) {
            println(this)
            assertEquals(constant(1), this)
        }
    }

    @Test
    fun `simplify 1 * x`() {
        with(simplify(multiply(constant(1), variable("x")))) {
            println(this)
            assertEquals(variable("x"), this)
        }
    }

    @Test
    fun `simplify 2 * 3`() {
        with(simplify(multiply(constant(2), constant(3)))) {
            println(this)
            assertEquals(constant(6), this)
        }
    }

    @Test
    fun `simplify 3 * 2`() {
        with(simplify(multiply(constant(3), constant(2)))) {
            println(this)
            assertEquals(constant(6), this)
        }
    }

    @Test
    fun `simplify 2 * 3 + 4`() {
        with(simplify(add(multiply(constant(2), constant(3)), constant(4)))) {
            println(this)
            assertEquals(constant(10), this)
        }
    }

    @Test
    fun `simplify 2 * (3 + 4)`() {
        with(simplify(multiply(constant(2), add(constant(3), constant(4))))) {
            println(this)
            assertEquals(constant(14), this)
        }
    }

    @Test
    fun `simplify 2 * (3 * 4)`() {
        with(simplify(multiply(constant(2), multiply(constant(3), constant(4))))) {
            println(this)
            assertEquals(constant(24), this)
        }
    }

    @Test
    fun `simplify (2 * 3) * 4`() {
        with(simplify(multiply(multiply(constant(2), constant(3)), constant(4)))) {
            println(this)
            assertEquals(constant(24), this)
        }
    }

    @Test
    fun `simplify 7 + (0 * x + 1) * 3 + 12`() {
        with(simplify(add(constant(7), add(multiply(add(multiply(constant(0), variable("x")), constant(1)), constant(3)), constant(12))))) {
            println(this)
            assertEquals(constant(22), this)
        }
    }

    @Test
    fun `simplify 3 + x + 1 + y`() {
        val `3 + x + 1 + y` = add(constant(3), add(variable("x"), add(constant(1), variable("y"))))
        with(simplify(`3 + x + 1 + y`)) {
            println(this)
//            assertEquals(`3 + x + 1 + y`, this)
        }
    }


}
