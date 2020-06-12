package coroutines

import kotlinx.coroutines.*

class DBConnection

data class Row(val id: Int = 0)

suspend fun connectDB(): DBConnection {
    println("Creando conexion a base de datos…")
    delay(2000)
    return DBConnection()
}

suspend fun getInformation(connection: DBConnection): List<Row> {
    println("Obteniendo datos de la base de datos… $connection")
    delay(5000)
    return listOf(Row(1), Row(2))
}

fun processRow(row: Row): String {
    return "Id: ${row.id}"
}

suspend fun getById(connection: DBConnection, id: Int): Row {
    println("Solicitando informacion del ID: $id")
    return Row(id)
}

fun main() {
    println("main")
    startProcess()
    println("End of program")
}

fun startProcess() {
    val processResult = runBlocking {
        val job = launch {
            val connection = connectDB()
            val rows = async { getInformation(connection) }

            val rowsList = rows.await()

            rowsList.map {
                launch {
                    getById(connection, it.id)
                }
            }

        }
        if (/*El usuario presiono regresar */ true) {
            job.cancel()
        }

        val deferred: Deferred<List<String>> = async {
            val connection = connectDB()
            val rows = getInformation(connection)
            val processInfo = rows.map(::processRow)

            processInfo
        }

        deferred.await()
    }

    println("Resultado RunBlocking : $processResult")
}