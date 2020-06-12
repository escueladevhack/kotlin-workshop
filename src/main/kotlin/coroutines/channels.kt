package coroutines

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

data class Message(val from: String, val to: String, val body: String)

fun main() {
    val messageChannel = Channel<Message>()

    runBlocking {
        val receiveChannel = produce {
            send(Message("Ana", "Chuby", "IPN"))
        }

        receiveChannel.consumeEach {
            println(it)
        }

        launch {
            println("Hola, vamos a chatear")
            delay(4000)
            messageChannel.send(
                Message ("Sier", "Juan Guillermo", "Ya pasan de las 10 otra vez")
            )
            println("Mensaje enviado")
        }

        launch {
            println("Latinchat room")
            delay(2000)
            println("Mensajes?")
            println(messageChannel.receive())
            println(messageChannel.receive())
            messageChannel.close()
        }

        launch {
            println("Soy el entrometido")
            messageChannel.send(
                Message("Juan Guillermo", "Ana", "Que paso parse")
            )
        }

        launch {
            val childChannel = Channel<String>()

            launch {
                println("Soy la corutina 1")
                delay(2000)
                println("Coroutina 1 cacho el mensaje ${childChannel.receive()}")
            }

            launch {
                println("Soy la corutina 2")
                delay(2000)
                println("Coroutina 2 cacho el mensaje ${childChannel.receive()}")
                println("Coroutina 2 cacho el otro mensaje ${childChannel.receive()}")
            }

            launch {
                println("Soy la corutina 3")
                delay(2000)
                println("Coroutina 3 cacho el mensaje ${childChannel.receive()}")
            }

            launch {
                println("Soy la corutina 4")
                delay(2000)
                println("Coroutina 4 cacho el mensaje ${childChannel.receive()}")
            }

            childChannel.send("Mensaje A")
            childChannel.send("Mensaje B")
            childChannel.send("Mensaje C")
            childChannel.send("Mensaje D")
            childChannel.send("Mensaje E")

            childChannel.close()

            val numberChannel = Channel<Int>()

            launch {
                repeat(10) {
                    numberChannel.send(it)
                }
                numberChannel.close()
            }

            launch {
                for (number in numberChannel) {
                    println("Llego el id $number")
                }
            }
        }
    }
}