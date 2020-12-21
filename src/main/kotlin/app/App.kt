package app

import org.slf4j.LoggerFactory
import tests.testLexRec
import tests.testSimplify

object App {
    @JvmStatic
    fun main(args: Array<String>): Unit = with(LoggerFactory.getLogger(App.prettyTypeName())) {
        try {
            debug("started @ ${time()}")
            testSimplify()
            testLexRec()
        }
        catch(exception: Exception) {
            error(exception.flattenWithStackTrace())
        }
        finally {
            debug("stopped @ ${time()}")
        }
    }
}