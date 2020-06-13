package co.devhack.persistence

import java.io.File

actual class FileSystemManager {
    actual fun storeInFS(fileName: String, info: Any) {
        val file = File(getPath())
    }

    private fun getPath(): String {
        return ""
    }
}