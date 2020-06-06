package oop

import java.util.*
import kotlin.properties.Delegates

class FileAccess(_fileName: String) {
    private var lastModified: Date = Calendar.getInstance().time

    var fileName: String by Delegates.observable(_fileName) { prop, oldValue, newValue ->
        println("El archivo tenia el nombre: $oldValue y se modifico por ultima vez el: $lastModified")
        println("Ahora el archivo se llama $newValue")

        lastModified = Calendar.getInstance().time
    }
}

fun main() {
    val misDocumentos = FileAccess("MisDocs.txt")

    misDocumentos.fileName = "MisDocumnets_final.txt"

    Thread.sleep(10000)

    misDocumentos.fileName = "MisDocumnets_final_final.txt"

    Thread.sleep(5000)

    misDocumentos.fileName = "MisDocumnets_final_final_copia.txt"
}