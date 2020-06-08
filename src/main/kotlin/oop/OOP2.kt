package oop

/*  Representar en un programa lo siguiente
    1. Existen tres personajes, piloto, asistente de vuelo y pasajeros
    2. El piloto puede elevar, aterrizar y acelerar el avión
    3. Existe un auxiliar de vuelo
*/

abstract class Person(name: String, lastName: String) {

    private var _fullName: String = ""

    var fullName: String
        get() = _fullName
        set(value) {
            if (value.isNotEmpty())
                _fullName = value
        }

    init {
        _fullName = "$name $lastName"
    }

    constructor(name: String, lastName: String, age: Int) : this(name, lastName) {
        _fullName = "$name $lastName tiene $age años"
    }

    override fun toString() = _fullName

    abstract fun getRole(): String

}


class Pilot(name: String, lastName: String = "", private val experienceYears: Int) :
    Person(name, lastName) {

    companion object {
        const val COPILOTO = "copiloto"
        const val COMANDANTE = "comandante"
    }

    override fun getRole() =
        when (experienceYears) {
            in 1..5 -> COPILOTO
            else -> COMANDANTE
        }

    override fun toString() = "$fullName con $experienceYears y su rol ${getRole()}"

}


class FlightAttendant(
    name: String,
    lastName: String,
    private val age: Int
) : Person(name, lastName, age) {

    companion object {
        const val FLIGHT_ATTENDANT = "Flight Attendant"
    }

    override fun getRole() = FLIGHT_ATTENDANT

    override fun toString() = "${super.toString()} con role ${getRole()} y su edad $age"

}


interface Airplane {

    fun raising()

    fun landing()

    infix fun accelerate(velocity: Int)

    fun calling() {
        println("Llamando a la torre de control")
    }
}


class AirPlaneSmall(
    private val airCompany: String,
    private val brand: String,
    private val code: String,
    private val pilot: Person
) : Airplane {

    override fun raising() {
        println("Avión con código $code se esta elevando y el piloto es $pilot")
    }

    override fun landing() {
        println("El Avión con código $code esta aterrizando y el piloto es $pilot")
    }

    override fun accelerate(velocity: Int) {
        println("El Avión con código $code esta acelerando a $velocity y el piloto es $pilot")
    }

    override fun toString() = "Avion $brand con código $code de la compañia $airCompany y el piloto es $pilot"

}


enum class Roles(val code: Int) {
    PILOT(1),
    FLIGHT_ATTENDANT(2)
}

fun factoryPerson(role: Roles) =
    when (role) {
        Roles.PILOT -> Pilot("Juan", "Gómez", 8)
        Roles.FLIGHT_ATTENDANT -> FlightAttendant("Veronica", "Torres", 15)
    }


fun main() {
    val pilot = factoryPerson(Roles.PILOT)
    val pilot2 = factoryPerson(Roles.PILOT)
    val flightAttendant = factoryPerson(Roles.FLIGHT_ATTENDANT)
    val airPlaneSmall = AirPlaneSmall("Avianca", "Boeing", "0001", pilot)

    println("Piloto => $pilot")
    println("Piloto 2 => $pilot2")
    println("Flight Attendant => $flightAttendant")
    println("El vuelo => $airPlaneSmall")
    println(airPlaneSmall.raising())
    println(airPlaneSmall accelerate 800)
    println(airPlaneSmall.landing())
    println(airPlaneSmall.calling())
}