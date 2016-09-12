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

import com.vaadin.ui.*

fun hLayout(spacing: Boolean = false, margin: Boolean = false, init: HorizontalLayout.() -> Unit = {}) = HorizontalLayout().apply {
    setSpacing(spacing)
    setMargin(margin)
}.apply(init)

fun vLayout(spacing: Boolean = false, margin: Boolean = false, init: VerticalLayout.() -> Unit = {}) = VerticalLayout().apply {
    setSpacing(spacing)
    setMargin(margin)
}.apply(init)

internal fun <C : Component> C.addTo(container: ComponentContainer): C {
    container.addComponent(this)
    return this
}


