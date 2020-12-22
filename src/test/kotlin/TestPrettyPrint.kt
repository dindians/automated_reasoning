import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("test pretty printer")
internal class TestPrettyPrint {
    @Test
    fun `0`() {
        with(prettyPrint(defaultParser()("0"))) {
            println(this)
            assertEquals("0", this)
        }
    }

    @Test
    fun `00`() {
        with(prettyPrint(defaultParser()("00"))) {
            println(this)
            assertEquals("0", this)
        }
    }

    @Test
    fun `parse 01`() {
        with(prettyPrint(defaultParser()("01"))) {
            println(this)
            assertEquals("1", this)
        }
    }

    @Test
    fun `parse 234`() {
        with(prettyPrint(defaultParser()("234"))) {
            println(this)
            assertEquals("234", this)
        }
    }

    @Test
    fun `parse x + y`() {
        with(prettyPrint(defaultParser()("x + y"))) {
            println(this)
            assertEquals("x + y", this)
        }
    }

    @Test
    fun `parse x + y + z`() {
        with(prettyPrint(defaultParser()("x + y + z"))) {
            println(this)
            assertEquals("x + y + z", this)
        }
    }

    @Test
    fun `parse 6 * (x + y)`() {
        with(prettyPrint(defaultParser()("6 * (x + y)"))) {
            println(this)
            assertEquals("6 * (x + y)", this)
        }
    }

    @Test
    fun `parse (x1 + x2 + x3 + x4) * (y1 + y2 + y3)`() {
        with(prettyPrint(defaultParser()("(x1 + x2 + x3 + x4) * (y1 + y2 + y3)"))) {
            println(this)
            assertEquals("(x1 + x2 + x3 + x4) * (y1 + y2 + y3)", this)
        }
    }


}