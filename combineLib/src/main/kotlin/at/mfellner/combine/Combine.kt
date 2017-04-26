package at.mfellner.combine

object Combine {
    fun combine(child: LayoutChild, content: JsObject): LayoutChild {
        return when (child) {
            is Layout -> Layout(
                    child.type,
                    combine(child.properties, content),
                    child.children.map { combine(it, content) }
            )
            is RefChild -> StringChild(resolve(child.`$ref`, content).toString())
            is StringChild -> child
        }
    }

    fun combine(obj: JsObject, content: JsObject, parentKey: String = "$"): JsObject {
        val items = obj.value.map {
            val key = it.key
            val value = it.value

            if (key == "\$ref" && value is JsString) {
                Pair(parentKey, resolve(value.value, content))
            } else if (value is JsArray) {
                Pair(key, JsArray(value.value.map {
                    if (it is JsObject)
                        combine(it, content, key)
                    else
                        it
                }))
            } else if (value is JsObject) {
                Pair(key, combine(value, content, key))
            } else {
                Pair(key, value)
            }
        }.toTypedArray()

        return JsObject(mapOf(*items))
    }

    fun resolve(path: String, content: JsObject): JsValue {
        return JsString("PLACEHOLDER")
    }
}
