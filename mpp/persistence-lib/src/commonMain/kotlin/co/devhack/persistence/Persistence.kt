package co.devhack.persistence

class Persistence(
    private val fileSystemManager: FileSystemManager
) {
    fun saveInMemory() {
        println("")
    }

    fun saveInFileSystem(info: Any) {
        fileSystemManager.storeInFS("TMPFile.tmp", info)
    }

    private fun utilityMethod(){

    }

    internal  fun stateOfProcess(){

    }
}