package co.devhack.persistence

expect class FileSystemManager {
    fun storeInFS(fileName: String, info: Any)
}