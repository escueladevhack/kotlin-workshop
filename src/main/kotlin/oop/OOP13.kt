package oop

fun determineWrongGroup(age: Int) {
    if (age < 13) {
        println("Eres un@ chamac@")
    } else {
        if (age > 13 && age < 18) {
            println("Muchach@ pubert@")
        } else {
            if (age > 18 && age < 50) {
                println("Se te considera adulto")
            } else {
                println("Viejon")
            }
        }
    }
}

fun determineGroup(age: Age) {
    age.verifyValid()

    when (age.value) {
        in 1..13 -> println("Eres un niño")
        in 13..18 -> println("Eres un adolescente")
        in 18..50 -> println("Eres un adulto")
        else -> println("Eres un viejillo")
    }
}

interface AgeChanger

inline class Age(val value: Int): AgeChanger {
    fun verifyValid(){
        if(value !in 1..129) throw IllegalArgumentException("ESTA EDAD NO ES VALIDA!")
    }

}

fun main() {
    determineWrongGroup(10)
    determineWrongGroup(100)
    determineWrongGroup(-100)
    determineWrongGroup(-1)
    determineWrongGroup(-100000)

    determineGroup(Age(10))
    determineGroup(Age(100))
    determineGroup(Age(-100))
}