package ch.frankel.kaadin

import com.vaadin.server.*

fun Sizeable.height(height: String) = setHeight(height)
fun Sizeable.height(height: Float, unit: Sizeable.Unit) = setHeight(height, unit)
fun Sizeable.heightUndefined() = setHeightUndefined()
fun Sizeable.width(width: String) = setWidth(width)
fun Sizeable.width(width: Float, unit: Sizeable.Unit) = setWidth(width, unit)
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
fun Sizeable.sizeUndefined() = setSizeUndefined()
fun Sizeable.sizeFull() = setSizeFull()