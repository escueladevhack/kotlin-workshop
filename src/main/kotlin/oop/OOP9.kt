package oop

import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FileInformation {
    val directoryName: String by FileSystemDelegate()
    var fileSize: Int by FileInformationDelegate()

    var subDirectoriesCount: Int by FileInformationDelegate()
}

class FileSystemDelegate: ReadOnlyProperty<FileInformation, String> {
    override fun getValue(thisRef: FileInformation, property: KProperty<*>): String {
        // Conectarme a una computadora
        // Obtener el nombre de la carpeta donde me conecte

        return "MisPhotosDeJuanGuillermo"
    }
}

class FileInformationDelegate : ReadWriteProperty<FileInformation, Int> {
    override fun getValue(thisRef: FileInformation, property: KProperty<*>): Int {
        return when(property.name){
            "fileSize" -> 0
            else -> calculateSubdirectories()
        }
    }

    override fun setValue(thisRef: FileInformation, property: KProperty<*>, value: Int) {
        if(value < 0){
            throw IllegalArgumentException("NO PUEDES TENER VALOR NEGATIVOS, LOS ARCHIVOS TIENEN UN PESO")
        }

        when(property.name){
            "fileSize" -> println("Guardando nuevo valor")
            else -> calculateTotalSize(value)
        }
    }

    fun calculateSubdirectories(): Int{
        return 0
    }

    fun calculateTotalSize(value: Int){
        println("Accendiendo a subdirectorios")
    }
}






fun main() {
    val fileInformation = FileInformation()

    println(fileInformation.fileSize)

    fileInformation.fileSize = 9000

    fileInformation.subDirectoriesCount = 10
}

