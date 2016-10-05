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
import com.vaadin.ui.Notification.Type.*

/**
 * see http://demo.vaadin.com/sampler/#ui/interaction
 */
fun HasComponents.button(caption: String? = null,
                         icon: Resource? = null,
                         onClick: ((Button.ClickEvent) -> Unit)? = null,
                         init: Button.() -> Unit = {}) = Button()
        .addTo(this)
        .apply(init)
        .apply {
            caption?.let { this.caption = caption }
            icon?.let { this.icon = icon }
            onClick?.let { addClickListener(onClick) }
        }

fun HasComponents.nativeButton(caption: String? = null,
                               clickListener: ((Button.ClickEvent) -> Unit)? = null,
                               init: NativeButton.() -> Unit = {}) = NativeButton()
        .addTo(this)
        .apply(init)
        .apply {
            caption?.let { this.caption = caption }
            clickListener?.let { addClickListener(clickListener) }
        }

fun HasComponents.link(caption: String,
                       resource: Resource? = null,
                       targetName: String? = null,
                       width: Int = -1,
                       height: Int = -1,
                       border: BorderStyle = NONE,
                       init: Link.() -> Unit = {}) = Link()
        .addTo(this)
        .apply(init)
        .apply {
            this.caption = caption
            resource?.let { this.resource = resource }
            targetName?.let { this.targetName = targetName }
            this.targetWidth = width
            this.targetHeight = height
            this.targetBorder = border
        }

fun HasComponents.progressBar(progress: Float = 0f,
                              init: ProgressBar.() -> Unit = {}) = ProgressBar()
        .addTo(this)
        .apply(init)
        .apply {
            value = progress
            addTo(this@progressBar)
        }

fun HasComponents.progressBar(dataSource: Property<out Float>,
                              init: ProgressBar.() -> Unit = {}) = ProgressBar()
        .addTo(this)
        .apply(init)
        .apply {
            this.propertyDataSource = dataSource
            addTo(this@progressBar)
        }

fun HasComponents.menuBar(init: MenuBar.() -> Unit = {}): MenuBar {
    return MenuBar()
            .addTo(this)
            .apply(init)
}

// FIXME Works only with auto-open?
fun MenuBar.menuItem(caption: String, icon: Resource? = null,
                     onClick: (MenuBar.MenuItem) -> Unit = {},
                     checkable: Boolean = false,
                     checked: Boolean = false,
                     enabled: Boolean = true,
                     init: MenuItem.() -> Unit = {}) = addItem(caption, icon, onClick as Command)
        .apply {
            this.isCheckable = checkable
            this.isChecked = checked
            this.isEnabled = enabled
        }
        .apply(init)

fun MenuItem.menuItem(caption: String, icon: Resource? = null,
                      onClick: (MenuBar.MenuItem) -> Unit = {},
                      checkable: Boolean = false,
                      checked: Boolean = false,
                      enabled: Boolean = true,
                      init: MenuItem.() -> Unit = {}) = addItem(caption, icon, onClick as Command)
        .apply {
            this.isCheckable = checkable
            this.isChecked = checked
            this.isEnabled = enabled
        }
        .apply(init)

fun MenuItem.separator() = addSeparator()

private fun Notification.process(init: Notification.() -> Unit) { apply(init).show(Page.getCurrent()) }
fun show(message: String, description: String? = null, html: Boolean = false, init: Notification.() -> Unit = {}) = Notification(message, description, HUMANIZED_MESSAGE, html)
        .process(init)
fun warn(message: String, description: String? = null, html: Boolean = false, init: Notification.() -> Unit = {}) = Notification(message, description, WARNING_MESSAGE, html)
        .process(init)
fun error(message: String, description: String? = null, html: Boolean = false, init: Notification.() -> Unit = {}) = Notification(message, description, ERROR_MESSAGE, html)
        .process(init)
fun tray(message: String, description: String? = null, html: Boolean = false, init: Notification.() -> Unit = {}) = Notification(message, description, TRAY_NOTIFICATION, html)
        .process(init)
