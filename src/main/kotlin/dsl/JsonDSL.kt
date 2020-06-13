package dsl

fun main() {
    val devHackGroupJson = json {
        property {
            key = "workshop"
            value = "kotlin"
        }

        property(key = "instructors") {
            value = jsonArray("Juan Guillermo", "Sier")
        }
    }

    val danielInfo = json {
        property("name") { +"Daniel" }
        property(key = "lastName") { +"Espinoza" }
    }

    println(devHackGroupJson)
    println(danielInfo)
}

@DslMarker
annotation class JsonDSL

fun json(buildBlock: Json.() -> Unit): Json {
    return Json().apply(buildBlock)
}

@JsonDSL
class Json {
    private val _properties = mutableListOf<JsonProperty>()

    val properties: List<JsonProperty>
        get() = _properties

    fun property(key: String = "", buildBlock: JsonProperty.() -> Unit) {
        _properties.add(JsonProperty(key).apply(buildBlock))
    }

    override fun toString(): String {
        return properties.joinToString(",", "{", "}")
    }
}

@JsonDSL
data class JsonProperty(var key: String = "", var value: Any? = null) {
    operator fun plus(param: String) {
        value = param
    }

    operator fun Any?.unaryPlus() {
        value = this
    }
}

fun JsonProperty.jsonArray(vararg elements: Any): JsonArray {
    return JsonArray(listOf(*elements))
}

@JsonDSL
class JsonArray(private val elements: List<Any>) : List<Any> by elements {
    override fun toString(): String {
        return elements.joinToString(",", "[", "]")
    }
}

