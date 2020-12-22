import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("test lexical analysis")
internal class TestLex {
    @Test
    fun `analyze empty string`() {
        with(lex("")) {
            assertEquals(0, size)
        }
    }

    @Test
    fun `analyze random spaces`() {
        with(randomSpaces(10)) {
            println("analyzing random spaces [$this]")
            assertEquals(0, lex(this).size)
        }
    }

    @Test
    fun `analyze 1`() {
        with(lex("1")) {
            assertEquals(1, size)
            assertEquals("1", this[0])
        }
    }

    @Test
    fun `analyze 1 2`() {
        with(lex("1 2")) {
            assertEquals(2, size)
            assertEquals("1", this[0])
            assertEquals("2", this[1])
        }
    }

    @Test
    fun `analyze random alphanumeric word`() {
        with(randomAlphanumericString(10)) {
            println("analyzing random alphanumeric word [$this]")
            lex(this).also {
                assertEquals(1, it.size)
                assertEquals(this, it[0])
            }
        }
    }

    @Test
    fun `analyze random word + spaces + punctuation and symbolic chars`() {
        val randomWord = randomAlphanumericString(10)
        with(randomSpaces(4) + randomWord + " + ( 6 - 4 ) + { ab cd }   ") {
            println("analyzing random alphanumeric word [$this]")
            lex(this).also {
                assertEquals(12, it.size)
                assertEquals(randomWord, it[0])
                assertEquals("+", it[1])
                assertEquals("(", it[2])
                assertEquals("6", it[3])
                assertEquals("-", it[4])
                assertEquals("4", it[5])
                assertEquals(")", it[6])
                assertEquals("+", it[7])
                assertEquals("{", it[8])
                assertEquals("ab", it[9])
                assertEquals("cd", it[10])
                assertEquals("}", it[11])
            }
        }
    }
}