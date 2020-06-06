package oop

sealed class AnalyticEvent(val eventName: String) {

    data class WebClick(val id: String) : AnalyticEvent("click")

    class KeyboardEvent(val key: Char) : AnalyticEvent(key.toString()) {
        private val keysPressed = listOf<Char>()
    }

    object Logout : AnalyticEvent("Logout") {
        fun reportName() {
            println("El usuario se deslogueo")
        }
    }

    fun sayEvent() {
        println("Soy un $eventName")
    }
}

class ExternalEvent : AnalyticEvent("Weird") {
    fun hasInternet(    ): AnalyticEvent {
        //Logica…
        return Logout
    }
}

fun main() {
    val click: AnalyticEvent.WebClick = AnalyticEvent.WebClick("body")
    val secondClick: AnalyticEvent.WebClick = AnalyticEvent.WebClick("information")
    val keyboardEvent = AnalyticEvent.KeyboardEvent('C')

    val pingEvent = ExternalEvent()

    processAnalytic(pingEvent)
    processAnalytic(click)
    processAnalytic(secondClick)
    processAnalytic(keyboardEvent)
}

fun processAnalytic(event: AnalyticEvent) {
    when (event) {
        is AnalyticEvent.WebClick -> registerWebActivity(event.id)
        is AnalyticEvent.Logout -> {
            registerUserActivity()
            event.reportName()
        }
        is ExternalEvent -> println("Algo externo paso ${event.eventName}")
        is AnalyticEvent.KeyboardEvent -> println("El usuario presiono la tecla… ${event.eventName} ${event.key}")
    }

    event.sayEvent()
}

fun registerWebActivity(id: String) {
    println("El usuario le dio click al elemento $id")
}

fun registerUserActivity() {
    println("El usuario cerro sesion")
}
