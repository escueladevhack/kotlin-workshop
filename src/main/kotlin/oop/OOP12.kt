package oop

interface States {
    fun sayHi(){
        println("Hi")
    }
}

enum class ApplicationStates(val id: Int): States {
    Loading(0){
        override fun sayHi() {
            println("Perame, estoy cargando")
        }
    },
    Connected(1),
    Disconnected(2),
    Finished(5),
    Logged(10);

    fun advanceState(): ApplicationStates {
        return when(this.id){
            0 -> Connected
            1 -> Disconnected
            2 -> Finished
            5 -> Logged
            else -> Loading
        }
    }
}

fun main() {
    val myAppState = ApplicationStates.Loading

    myAppState.sayHi()

    println(myAppState)
    myAppState.advanceState().sayHi()

    myAppState.advanceState().advanceState().sayHi()

    println(ApplicationStates.values().joinToString())
}