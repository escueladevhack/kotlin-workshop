package oop

fun main() {
    TODO("Arregla este codigo para que el programa compile")

    val juanGuillermo = User(1, "Juan Guillermo", "Gomez")
    val sierisimo = User(2, "Sierisimo", "Sier")

    val pendientes = TodoList()

    //We want this API… que hueva repetir todos los metodos
    pendientes.add(TodoTask("Explicar delegation", sierisimo))
    pendientes.add(TodoTask("Interrumpir a quien sea", sierisimo))
    pendientes.add(TodoTask("Hablar sobre Cali", juanGuillermo))

    pendientes[3].status = TodoStatuses.Done

    //…porque en realidad nos interesan estos otros
    pendientes.markAsDone(1)
    pendientes.markAsInProgress(2)


    pendientes.markAs(TaskId(2), TodoStatuses.Done)

    pendientes.reassing(3, juanGuillermo)

    pendientes.removeWithId(1)
}