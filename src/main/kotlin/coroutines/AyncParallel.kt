package coroutines

import java.sql.Connection

//Nuestro propio thread

class WorkShopThread(val operation: () -> Unit) : Thread() {
    override fun run() {
        operation()
    }
}

fun main() {
    val myThread = WorkShopThread {
        println("Trabajo pesado…")
    }

    myThread.start()

    connect { connect ->
        fetch { list ->
            process(list) { info ->
                present(info) { presented ->
                    storeLocal(presented)
                    println(info)
                }
            }
        }
    }
}

fun connect(onConnect: (Connection) -> Unit) {
    println("Connecting…")
    //un monton de configuraciones
    //un monton de objetos
    //onConnect(…)
}

fun fetch(onFecht: (List<String>) -> Unit) {

}

fun process(list: List<String>, onProcces: (String) -> Unit) {

}

fun storeLocal(info: String) {

}

fun present(info: String, callBack: (String) -> Unit) {
}