package automated_reasoning.app

import org.slf4j.LoggerFactory

object App {
    @JvmStatic
    fun main(args: Array<String>): Unit = with(LoggerFactory.getLogger(App.prettyTypeName())) {
        try {
            debug("started @ ${time()}")
            info("nothing to do yet")
        }
        catch(exception: Exception) {
            error(exception.flattenWithStackTrace())
        }
        finally {
            debug("stopped @ ${time()}")
        }
    }
}
