package oop

import kotlin.properties.Delegates

class Student {
    val grades: String = ""
}

class StudentList(
    private val students: MutableList<Student> = mutableListOf()
) : MutableList<Student> by students {

    fun getApprovedStudents(): List<Student> {
        return students.filter { it.grades == "A" }
    }

}

class User(
    val username: String,
    name: String,
    lastName: String
) : Person(name, lastName) {
    override fun getRole() = username
}

class Workshop(presenters: Map<String, Any>) {
    val juanGuillermo: Person by presenters
    val sierisimo: Person by presenters

    private val presentadores: MutableList<User>
            = mutableListOf(User("jggomez", "Juan Guillermo", "Gomez"))

    var presentadorDelDia: User
            by Delegates.observable(presentadores.first()) { prop, oldValue, newValue ->
                println("La sesion anterior, presento $oldValue")
                println("Hoy presenta $newValue")

                presentadores.add(newValue)
            }

    fun mencionarPresentadores(){
        println("Los presentadores han sido: ${presentadores.joinToString()}")
    }
}

fun main() {
    val myStudents = StudentList()
    myStudents.add(Student())
    myStudents.addAll(listOf(Student()))
    myStudents.getApprovedStudents()

    val users = mapOf<String, Person>(
        "sierisimo" to User("Sier", "Sinuhe", "Jaime"),
        "juanGuillermo" to User("jggomez", "Juan Guillermo", "Gomez"),
        "anaColin" to User("AnaC", "Ana", "Colin")
    )

    val kotlinWorkshop = Workshop(users)
    println(kotlinWorkshop.sierisimo)
    println(kotlinWorkshop.juanGuillermo)

    kotlinWorkshop.mencionarPresentadores()
    kotlinWorkshop.presentadorDelDia = User("sier", "Sinuhe", "Jaime")
    kotlinWorkshop.mencionarPresentadores()
}




class EquipoDeFutbol(
    jugadores: Map<String, Person>,
    administradores: Map<String, Person>
){
    private val delantero: Person by jugadores
    private val portero: Person by jugadores

    private val directorTecnico: Person by administradores
}

interface Actuar
interface Reportar

class Actor : Actuar
class Columnista : Reportar

class Celebridad(
    val actor: Actor,
    val columnista: Columnista
): Actuar by actor, Reportar by columnista