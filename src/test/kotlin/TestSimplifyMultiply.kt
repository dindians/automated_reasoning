import automated_reasoning.simplify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("simplify multiply")
internal class TestSimplifyMultiply {
    @Test
    fun `simplify 0 * 0`() = with(simplify(multiply(constant(0), constant(0)))) {
        println(this)
        assertEquals(constant(0), this)
    }

    @Test
    fun `simplify 0 * 1`() =  with(simplify(multiply(constant(0), constant(1)))) {
        println(this)
        assertEquals(constant(0), this)
    }

    @Test
    fun `simplify 0 * x`() = with(simplify(multiply(constant(0), variable("x")))) {
        println(this)
        assertEquals(constant(0), this)
    }

    @Test
    fun `simplify 1 * 1`() = with(simplify(multiply(constant(1), constant(1)))) {
        println(this)
        assertEquals(constant(1), this)
    }

    @Test
    fun `simplify 1 * x`() = with(simplify(multiply(constant(1), variable("x")))) {
        println(this)
        assertEquals(variable("x"), this)
    }

    @Test
    fun `simplify 2 * 3`() = with(simplify(multiply(constant(2), constant(3)))) {
        println(this)
        assertEquals(constant(6), this)
    }

    @Test
    fun `simplify 3 * 2`() = with(simplify(multiply(constant(3), constant(2)))) {
        println(this)
        assertEquals(constant(6), this)
    }

    @Test
    fun `simplify 2 * (3 * 4)`() = with(simplify(multiply(constant(2), multiply(constant(3), constant(4))))) {
        println(this)
        assertEquals(constant(24), this)
    }

    @Test
    fun `simplify (2 * 3) * 4`() = with(simplify(multiply(multiply(constant(2), constant(3)), constant(4)))) {
        println(this)
        assertEquals(constant(24), this)
    }
}
