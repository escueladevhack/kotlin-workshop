package oop

class CourseListDevHack(
    private val courseList: MutableList<String> = mutableListOf<String>()
) : MutableList<String> by courseList

fun main() {
    val courseListDevHack = CourseListDevHack()

    courseListDevHack.get(0)
    courseListDevHack.add("Ktor")
}