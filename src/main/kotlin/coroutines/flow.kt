package coroutines

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

suspend fun printOrigin(message: Message) {
    println("Lo envio ${message.from}")
}

fun main() {
    runBlocking {
        createProfiles(
            listOf("Sier", "Juan Guillermo", "Daniel", "Ana", "Chuby", "Sierisimo")
                .filter { !it.toLowerCase().contains("sier") }
                .asFlow()
        )
    }
}

suspend fun createProfiles(profilesFlow: Flow<String>) {
    profilesFlow.collect {
        println("Creando perfil para $it")
    }
}

fun obtainMessage() = flow {
    repeat(5) {
        val message = Message("Sier", "Juan Guillermo", "Deberiamos terminar en no mas de $it minutos")
        printOrigin(message)
        emit(message)
    }
}