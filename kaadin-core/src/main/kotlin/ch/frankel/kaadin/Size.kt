/*
 * Copyright 2016 Nicolas Fränkel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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