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

class CustomComponentWrapper : CustomComponent() {
    public override fun setCompositionRoot(compositionRoot: Component) {
        super.setCompositionRoot(compositionRoot)
    }
}

fun <T: CustomComponent> HasComponents.customComponent(customComponent: T, init: CustomComponent.() -> Unit = {}) = customComponent
        .apply(init)
        .addTo(this)
fun <T: Component> HasComponents.customComponent(component: T, init: CustomComponent.() -> Unit = {}) = CustomComponent(component)
        .apply(init)
        .addTo(this)
fun HasComponents.customComponent(init: CustomComponentWrapper.() -> Unit = {}) = CustomComponentWrapper()
        .apply(init)
        .addTo(this)