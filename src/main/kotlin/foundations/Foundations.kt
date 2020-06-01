package foundations

import kotlin.math.absoluteValue
import kotlin.math.roundToInt

/*  Programa para evaluar el nivel de ingles
    1. El usuario digite su nombre
    2. El sistema generar una pregunta y mostrar al usuario
    3. Usuario responde la pregunta
    4. El sistema guarda la respuesta del usuario
    5. Se repite el paso 2 al paso 4 por 5 veces.
    6. Sistema imprime el número de respuestas correctas e incorrectas y el rendimiento
        el cual se calcula Número de respuestas correctas / el total de preguntas
*/

val questions = Array(5) { "" }
val answers = Array(5) { "" }
val correctAnswers = Array(5) { 0 }

fun initQuestions() {
    questions[0] = "For ________ Numbers"
    questions[1] = "For ________ Numbers"
    questions[2] = "For ________ Numbers"
    questions[3] = "For ________ Numbers"
    questions[4] = "For ________ Numbers"
}

fun initAnswers() {
    answers[0] = "1. name | 2. user | 3. string | 4. integer"
    answers[1] = "1. name | 2. user | 3. string | 4. integer"
    answers[2] = "1. name | 2. user | 3. string | 4. integer"
    answers[3] = "1. name | 2. user | 3. string | 4. integer"
    answers[4] = "1. name | 2. user | 3. string | 4. integer"
}

fun initCorrectAnswers() {
    correctAnswers[0] = 1
    correctAnswers[1] = 2
    correctAnswers[2] = 1
    correctAnswers[3] = 4
    correctAnswers[4] = 3
}

fun requestName(): String {
    print("Por favor digita tu nombre: ")
    return readLine() ?: ""
}

fun printQuestionAndAnswers(question: String, answers: String): Int {
    println(question)
    println(answers)
    return readLine()?.toInt() ?: 0
}

fun createQuestions(userName: String): ArrayList<Int> {
    val userAnswers = ArrayList<Int>()
    println("Por favor responde ${userName.toUpperCase()}")
    for ((index, question) in questions.withIndex()) {
        val answer = (printQuestionAndAnswers(
            question,
            answers[index]
        ))
        if (validateAnswer(answer)) userAnswers.add(answer) else userAnswers.add(0)
    }
    return userAnswers
}

fun validateAnswer(answer: Int) = answer in 1..4

fun getCorrectAnswers(userAnswers: List<Int>) =
    userAnswers.filterIndexed { index, answer ->
        answer == correctAnswers[index]
    }.count()

fun getIncorrectAnswers(userAnswers: List<Int>) =
    (getCorrectAnswers(userAnswers) - userAnswers.size).absoluteValue

fun calculatePerformanceScore(userCorrectAnswers: Int, totalQuestions: Int) =
    (userCorrectAnswers.toDouble().div(totalQuestions.toDouble()) * 100).roundToInt()

fun calculateLevel(performanceScore: Int) =
    when (performanceScore) {
        in 0..40 -> "Principiante"
        in 41..80 -> "Intermedio"
        in 81..100 -> "Avanzado"
        else -> "Ninguno"
    }

fun main() {
    initQuestions()
    initAnswers()
    initCorrectAnswers()
    val userName = requestName()

    for (iteration in 1..4) {
        println("**********************************")
        println("Esta es la ronda => $iteration")
        val userAnswers = createQuestions(userName)
        val userCorrectAnswers = getCorrectAnswers(userAnswers)
        val userInCorrectAnswers = getIncorrectAnswers(userAnswers)
        val userPerformanceScore =
            calculatePerformanceScore(userCorrectAnswers, userAnswers.size)
        println("El número de respuestas correctas fueron => $userCorrectAnswers")
        println("El número de respuestas incorrectas fueron => $userInCorrectAnswers")
        println("El puntaje fue de => $userPerformanceScore %")
        println("Su nivel es => ${calculateLevel(userPerformanceScore)}")
    }
}

