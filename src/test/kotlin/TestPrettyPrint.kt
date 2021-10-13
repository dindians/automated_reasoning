import automated_reasoning.defaultParser
import automated_reasoning.prettyPrint
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

@DisplayName("pretty printing")
internal class TestPrettyPrint {
    @Test
    fun `"pretty print 0"`() = with(prettyPrint(defaultParser()("0"))) {
            println(this)
            assertEquals("0", this)
        }

    @Test
    fun `"pretty print 00"`() = with(prettyPrint(defaultParser()("00"))) {
            println(this)
            assertEquals("0", this)
        }

    @Test
    fun `"pretty print 01"`() = with(prettyPrint(defaultParser()("01"))) {
            println(this)
            assertEquals("1", this)
        }

    @Test
    fun `"pretty print 234"`() = with(prettyPrint(defaultParser()("234"))) {
            println(this)
            assertEquals("234", this)
        }

    @Test
    fun `"pretty print x + y"`() = with(prettyPrint(defaultParser()("x + y"))) {
            println(this)
            assertEquals("x + y", this)
        }

    @Test
    fun `"pretty print x + y + z"`() = with(prettyPrint(defaultParser()("x + y + z"))) {
            println(this)
            assertEquals("x + y + z", this)
        }

    @Test
    fun `"pretty print (x + y) + z"`() = with(prettyPrint(defaultParser()("(x + y) + z"))) {
            println(this)
            assertEquals("x + y + z", this)
        }

    @Test
    fun `"pretty print x + (y + z)"`() = with(prettyPrint(defaultParser()("x + (y + z)"))) {
            println(this)
            assertEquals("x + (y + z)", this)
        }

    @Test
    fun `"pretty print ((a + b) + c) + d"`() = with(prettyPrint(defaultParser()("((a + b) + c) + d"))) {
            println(this)
            assertEquals("a + b + c + d", this)
        }

    @Test
    fun `"pretty print (a + b) + (c + d)"`() = with(prettyPrint(defaultParser()("(a + b) + (c + d)"))) {
            println(this)
            assertEquals("a + b + (c + d)", this)
        }

    @Test
    fun `"pretty print a + (b + (c + d))"`() = with(prettyPrint(defaultParser()("a + (b + (c + d))"))) {
            println(this)
            assertEquals("a + (b + (c + d))", this)
        }

    @Test
    fun `"pretty print a * b"`() = with(prettyPrint(defaultParser()("a * b"))) {
            println(this)
            assertEquals("a * b", this)
        }

    @Test
    fun `"pretty print a * b * c"`() = with(prettyPrint(defaultParser()("a * b * c"))) {
            println(this)
            assertEquals("a * (b * c)", this)
        }

    @Test
    fun `"pretty print (a * b) * c"`() = with(prettyPrint(defaultParser()("(a * b) * c"))) {
            println(this)
            assertEquals("a * b * c", this)
        }

    @Test
    fun `"pretty print a * (b * c)"`() = with(prettyPrint(defaultParser()("a * (b * c)"))) {
            println(this)
            assertEquals("a * (b * c)", this)
        }

    @Test
    fun `"pretty print ((a * b) * c) * d"`() = with(prettyPrint(defaultParser()("((a * b) * c) * d"))) {
            println(this)
            assertEquals("a * b * c * d", this)
        }

    @Test
    fun `"pretty print (a * b) * (c * d)"`() = with(prettyPrint(defaultParser()("(a * b) * (c * d)"))) {
            println(this)
            assertEquals("a * b * (c * d)", this)
        }

    @Test
    fun `"pretty print a * ((b * c) * d)"`() = with(prettyPrint(defaultParser()("a * ((b * c) * d)"))) {
            println(this)
            assertEquals("a * (b * c * d)", this)
        }

    @Test
    fun `"pretty print a * (b * (c * d))"`() = with(prettyPrint(defaultParser()("a * (b * (c * d))"))) {
            println(this)
            assertEquals("a * (b * (c * d))", this)
        }

    @Test
    fun `"pretty print 6 * (x + y)"`() = with(prettyPrint(defaultParser()("6 * (x + y)"))) {
            println(this)
            assertEquals("6 * (x + y)", this)
        }

    @Test
    fun `"pretty print (a * b) + (x * y)"`() = with(prettyPrint(defaultParser()("(a * b) + (x * y)"))) {
            println(this)
            assertEquals("a * b + x * y", this)
        }

    @Test
    fun `"pretty print (x1 + x2 + x3 + x4) * (y1 + y2 + y3)"`() = with(prettyPrint(defaultParser()("(x1 + x2 + x3 + x4) * (y1 + y2 + y3)"))) {
            println(this)
            assertEquals("(x1 + x2 + x3 + x4) * (y1 + y2 + y3)", this)
        }

    @Test
    fun `long expression`() = with(prettyPrint(defaultParser()("a + b * c + d * ( e + f) + g * (h + i * j) + (k) + ((l)) + (m) * (n) + (o + p) + r"))) {
            println(this)
        }
}
