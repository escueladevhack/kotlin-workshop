package oop

import java.util.*

fun isDateToday(date: Date): Boolean {
    val today = Calendar.getInstance().time

    val isSameDay = today.day == date.day
    val isSameMonth = today.month == date.month
    val isSameYear = today.year == date.year

    return isSameDay && isSameMonth && isSameYear
}


fun Date?.isToday(): Boolean {
    if(this == null) return false

    val today = Calendar.getInstance().time

    val isSameDay = today.day == this.day
    val isSameMonth = today.month == this.month
    val isSameYear = today.year == this.year

    return isSameDay && isSameMonth && isSameYear
}


val Date.monthName: String
    get() {
        return when(month){
            12 -> "Navidad"
            else -> "NoNavidad"
        }
    }


fun Any.sayHi(){
    println("Hola, soy $this")
}

fun main() {
    val calendar = Calendar.getInstance()
    calendar.set(2019, 10, 2)

    val octuber2 = calendar.time

    println("Hoy es 2 de octubre? ${isDateToday(octuber2)}")
    println("Hoy es hoy? ${isDateToday(Calendar.getInstance().time)}")

    println("Esta fecha es hoy? ${octuber2.isToday()}")

    val date: Date? = null

    date.isToday()

    octuber2.sayHi()

    println("El mes de octuber2 es: ${octuber2.monthName}")
}


class Sier {
    private var freetime: Int = 0

    fun addTime(time: Int){

    }
}

fun Sier.addFreetime(time: Int){
    addTime(time)
}