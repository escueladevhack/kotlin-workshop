package oop

object UtilFile {

    const val NAME_FILE = "test.txt"

    fun read() {
        println("Reader File")
    }

    fun write() {
        println("Write File")
    }

    fun open() {
        println("Open File")
    }
}

fun main() {
    UtilFile.open()
    UtilFile.read()
    UtilFile.write()
}