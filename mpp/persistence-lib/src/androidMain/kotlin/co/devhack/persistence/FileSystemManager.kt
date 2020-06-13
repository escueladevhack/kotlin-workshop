package co.devhack.persistence

actual class FileSystemManager {
    actual fun storeInFS(fileName: String, info: Any) {
        // Guardar el info a Room
        val storable = Storable(fileName, info.toString())
    }
}