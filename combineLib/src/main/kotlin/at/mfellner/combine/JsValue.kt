package at.mfellner.combine

sealed class JsValue

object JsNull : JsValue()

data class JsString(val value: String) : JsValue()

data class JsNumber(val value: Double) : JsValue()

data class JsBoolean(val value: Boolean) : JsValue()

data class JsArray(val value: List<JsValue>) : JsValue()

data class JsObject(val value: Map<String, JsValue>) : JsValue()
