package ch.frankel.kaadin

import com.vaadin.server.*

fun Sizeable.height(height: String) = setHeight(height)
fun Sizeable.height(height: Float, unit: Sizeable.Unit) = setHeight(height, unit)
fun Sizeable.height(height: Height) = setHeight(height.size, height.unit)
fun Sizeable.heightUndefined() = setHeightUndefined()
fun Sizeable.width(width: String) = setWidth(width)
fun Sizeable.width(width: Float, unit: Sizeable.Unit) = setWidth(width, unit)
fun Sizeable.width(width: Width) = setWidth(width.size, width.unit)
fun Sizeable.widthUndefined() = setWidthUndefined()
fun Sizeable.size(width: String, height: String) {
    width(width)
    height(height)
}
fun Sizeable.size(width: Float, height: Float, unit: Sizeable.Unit) {
    width(width, unit)
    height(height, unit)
}
fun Sizeable.size(width: Float, widthUnit: Sizeable.Unit, height: Float, heightUnit: Sizeable.Unit) {
    width(width, widthUnit)
    height(height, heightUnit)
}
fun Sizeable.size(width: Width, height: Height) {
    width(width)
    height(height)
}
fun Sizeable.sizeUndefined() = setSizeUndefined()
fun Sizeable.sizeFull() = setSizeFull()

open class Size(val size: Float, val unit: Sizeable.Unit)
class Height(size: Float, unit: Sizeable.Unit) : Size(size, unit)
class Width(size: Float, unit: Sizeable.Unit) : Size(size, unit)
