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
import com.vaadin.ui.*

/**
 * http://demo.vaadin.com/sampler/#ui/structure
 */
fun HasComponents.panel(caption: String? = null, init: Panel.() -> Unit = {}) = Panel()
        .apply {
            caption?.let { this.caption = caption }
            content?.let { this.content = content }
        }.apply(init)
        .addTo(this)

fun HasComponents.verticalSplitPanel(init: VerticalSplitPanel.() -> Unit = {}) = VerticalSplitPanel()
        .apply(init)
        .addTo(this)

fun HasComponents.horizontalSplitPanel(init: HorizontalSplitPanel.() -> Unit = {}) = HorizontalSplitPanel()
        .apply(init)
        .addTo(this)

fun HasComponents.accordion(init: Accordion.() -> Unit = {}) = Accordion()
        .apply(init)
        .addTo(this)


fun HasComponents.tabSheet(init: TabSheet.() -> Unit = {}) = TabSheet()
        .apply(init)
        .addTo(this)

fun TabSheet.tab(caption: String? = null, icon: Resource? = null, init: TabContainer.() -> Unit = {}) = TabContainer(this, caption, icon).apply(init)

/** Used in InternalUtils.kt. */
class TabContainer(internal val tabSheet: TabSheet,
                   internal val caption: String?,
                   internal val icon: Resource?) : AbstractSingleComponentContainer()

// TODO handle setting the component in the lambda
fun HasComponents.popupView(content: PopupView.Content, init: PopupView.() -> Unit = {}) = PopupView(content).apply(init).addTo(this)
fun HasComponents.popupView(small: String, large: Component, init: PopupView.() -> Unit = {}) = PopupView(small, large).apply(init).addTo(this)