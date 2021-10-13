import automated_reasoning.simplify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

@DisplayName("simplify combination of add and multiply")
internal class TestSimplify {
    @Test
    fun `simplify 2 * 3 + 4`() = with(simplify(add(multiply(constant(2), constant(3)), constant(4)))) {
            println(this)
            assertEquals(constant(10), this)
        }

    @Test
    fun `simplify 2 * (3 + 4)`() = with(simplify(multiply(constant(2), add(constant(3), constant(4))))) {
            println(this)
            assertEquals(constant(14), this)
        }

    @Test
    fun `simplify 7 + (0 * x + 1) * 3 + 12`() = with(simplify(add(constant(7), add(multiply(add(multiply(constant(0), variable("x")), constant(1)), constant(3)), constant(12))))) {
            println(this)
            assertEquals(constant(22), this)
        }
}
