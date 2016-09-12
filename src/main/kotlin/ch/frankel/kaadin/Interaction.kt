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

import com.vaadin.data.*
import com.vaadin.server.*
import com.vaadin.shared.ui.*
import com.vaadin.shared.ui.BorderStyle.*
import com.vaadin.ui.*
import com.vaadin.ui.MenuBar.*

/**
 * see http://demo.vaadin.com/sampler/#ui/interaction
 */
fun ComponentContainer.button(caption: String? = null,
                              icon: Resource? = null,
                              clickListener: ((Button.ClickEvent) -> Unit)? = null,
                              init: Button.() -> Unit = {}): Button {
    return Button()
            .addTo(this)
            .apply(init)
            .apply {
                caption?.let { this.caption = caption }
                icon?.let { this.icon = icon }
                clickListener?.let { addClickListener(clickListener) }
            }
}

fun ComponentContainer.nativeButton(caption: String? = null,
                                    clickListener: ((Button.ClickEvent) -> Unit)? = null,
                                    init: NativeButton.() -> Unit = {}): NativeButton {
    return NativeButton()
            .addTo(this)
            .apply(init)
            .apply {
                caption?.let { this.caption = caption }
                clickListener?.let { addClickListener(clickListener) }
            }
}

fun ComponentContainer.link(caption: String? = null,
                            resource: Resource? = null,
                            targetName: String? = null,
                            width: Int = -1,
                            height: Int = -1,
                            border: BorderStyle = NONE,
                            init: Link.() -> Unit = {}): Link {
    return Link()
            .addTo(this)
            .apply(init)
            .apply {
                caption?.let { this.caption = caption }
                resource?.let { this.resource = resource }
                targetName?.let { this.targetName = targetName }
                this.targetWidth = width
                this.targetHeight = height
                this.targetBorder = border
            }
}

fun ComponentContainer.progressBar(progress: Float = 0f,
                                   dataSource: Property<out Any>? = null,
                                   init: ProgressBar.() -> Unit = {}): ProgressBar {
    return ProgressBar()
            .addTo(this)
            .apply(init)
            .apply {
                value = progress
                dataSource?.let { this.propertyDataSource = dataSource }
                addComponent(this)
            }
}

fun ComponentContainer.menuBar(init: MenuBar.() -> Unit = {}): MenuBar {
    return MenuBar()
            .addTo(this)
            .apply(init)
}

fun MenuBar.menuItem(caption: String, icon: Resource? = null, command: Command, init: MenuItem.() -> Unit = {}): MenuItem {
    return addItem(caption, icon, command).apply(init)
}

// Notification is not a component that can be added to the layout