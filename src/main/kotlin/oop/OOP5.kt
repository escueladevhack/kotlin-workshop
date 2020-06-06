package oop

class SierAirplane(
    airplane: Airplane
) : Airplane by airplane {
    override fun accelerate(velocity: Int) {
        println("METELE $velocity DE TURBO CARNAL!!!!!!")
    }
}

fun main() {
    val avionDeSier =
        SierAirplane(
            AirPlaneSmall("Devhack", "Sier", "SR", Pilot("Sinuhe", "Jaime", -10))
        )

    avionDeSier.accelerate(10)
}