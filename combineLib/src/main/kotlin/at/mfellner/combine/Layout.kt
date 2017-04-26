package at.mfellner.combine

sealed class LayoutChild

data class Layout(val type: String,
                  val properties: JsObject,
                  val children: List<LayoutChild>) : LayoutChild()

data class StringChild(val value: String) : LayoutChild()

data class RefChild(val `$ref`: String) : LayoutChild()
