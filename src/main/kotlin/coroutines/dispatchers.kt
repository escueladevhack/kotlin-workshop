package coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

suspend fun saveOnFile(fileName: String, information: String) {
    println("Se creo el archivo $fileName y se le puso el contenido de $information")
}

fun main() {
    runBlocking {
        launch {
            val connection = connectDB()
            val rows = getInformation(connection)
            rows.forEach {
                withContext(Dispatchers.Default) {
                    saveOnFile("File${it.id}", processRow(it))
                }
            }
        }
    }

    val dbWorker = DBWorker()
    dbWorker.getOnConnection {
        //
    }
}

class DBWorker : CoroutineScope {
    private val dbJob = Job()

    override val coroutineContext: CoroutineContext
        get() = dbJob + Dispatchers.IO

    fun getOnConnection(connectionBlock: suspend (List<Row>) -> Unit): Job {

        return launch {
            connectionBlock(emptyList())
        }
    }

    private fun onError() {
        dbJob.cancel()
    }
}