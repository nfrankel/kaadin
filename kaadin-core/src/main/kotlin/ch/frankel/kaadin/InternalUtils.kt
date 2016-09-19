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

internal fun <C : Component> C.addTo(container: HasComponents): C {
    when {
        container is SingleComponentContainer && container.componentCount > 0 -> throw IllegalArgumentException("$container can only have a single child")
        container is TabContainer -> container.tabSheet.addTab(this, container.caption, container.icon)
        container is SingleComponentContainer -> container.content = this
        container is AbstractSplitPanel && container.componentCount == 0 -> container.firstComponent = this
        container is AbstractSplitPanel && container.componentCount == 1 -> container.secondComponent = this
        container is AbstractSplitPanel && container.componentCount > 1 -> throw IllegalArgumentException("$container can only have a 2 children")
        container is ComponentContainer -> container.addComponent(this)
        else -> throw IllegalArgumentException("Cannot add $this to $container")
    }
    return this
}

