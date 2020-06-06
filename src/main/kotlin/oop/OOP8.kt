package oop

import kotlin.properties.Delegates

class FileAccessV1(_fileName: String) {
    var fileName: String by Delegates.vetoable(_fileName) { _, oldName, newName ->
        newName.endsWith(".txt")
    }
}

fun main() {
    val misDocumentos = FileAccessV1("MisDocs.txt")

    misDocumentos.fileName = "MisDocumnets_final.txt"

    misDocumentos.fileName = "MisDocumnets_final_final.txt"

    misDocumentos.fileName = "MisDocumnets_final_final_copia.txt"

    misDocumentos.fileName = "MisPhotos.png"
    misDocumentos.fileName = "MisPhotos.jpg"

    misDocumentos.fileName = "FotosComprometedoras.txt"

    misDocumentos.fileName = "Fotos2.txtt"

    misDocumentos.fileName = "MisPhotos.exe"

    println(misDocumentos.fileName)
}