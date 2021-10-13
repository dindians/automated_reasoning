import automated_reasoning.simplify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("simplify add")
internal class TestSimplifyAdd {
    @Test
    fun `simplify 0 + 0`() = with(simplify(add(constant(0), constant(0)))) {
        println(this)
        assertEquals(constant(0), this)
    }

    @Test
    fun `simplify 0 + 1`() = with(simplify(add(constant(0), constant(1)))) {
        println(this)
        assertEquals(constant(1), this)
    }

    @Test
    fun `simplify 1 + 0`() = with(simplify(add(constant(1), constant(0)))) {
        println(this)
        assertEquals(constant(1), this)
    }

    @Test
    fun `simplify 1 + 2`() = with(simplify(add(constant(1), constant(2)))) {
        println(this)
        assertEquals(constant(3), this)
    }

    @Test
    fun `simplify x + 0`() = with(simplify(add(variable("x"), constant(0)))) {
        println(this)
        assertEquals(variable("x"), this)
    }

    @Test
    fun `simplify (2 + 3) + 4`() = with(simplify(add(add(constant(2), constant(3)), constant(4)))) {
        println(this)
        assertEquals(constant(9), this)
    }

    @Test
    fun `simplify 2 + (3 + 4)`() = with(simplify(add(constant(2), add(constant(3), constant(4))))) {
        println(this)
        assertEquals(constant(9), this)
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
