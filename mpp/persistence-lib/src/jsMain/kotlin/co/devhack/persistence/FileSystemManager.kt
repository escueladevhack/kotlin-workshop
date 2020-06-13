package co.devhack.persistence

external fun require(module: String): dynamic

actual class FileSystemManager {
    private val fs = require("fs")

    actual fun storeInFS(fileName: String, info: Any) {
        fs.saveFileAsync(info) {

        }
    }
}