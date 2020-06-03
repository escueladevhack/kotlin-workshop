package oop

class AdHoc() {
    private fun useAdHoc() = object {
        var x = 0
        val y = 4
        val z = 5
    }

    fun adHoc() {
        useAdHoc().x
        useAdHoc().y
        useAdHoc().z
    }
}

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