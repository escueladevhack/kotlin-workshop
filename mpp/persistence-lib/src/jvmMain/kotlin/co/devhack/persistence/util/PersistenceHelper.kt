package co.devhack.persistence.util

import co.devhack.persistence.Persistence

class PersistenceHelper(
    private val persistence: Persistence
) {
    fun optimizeForMemory() {
        persistence.saveInMemory()
    }
}