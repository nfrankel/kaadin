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
import com.vaadin.ui.*
import com.vaadin.ui.Calendar
import com.vaadin.ui.components.calendar.event.*
import java.util.*

private fun <F : AbstractField<T>, T : Any> F.process(container: ComponentContainer,
                                                      caption: String?,
                                                      value: T?,
                                                      dataSource: Property<out Any>?,
                                                      init: F.() -> Unit): F {
    return apply {
        caption?.let { this.caption = caption }
        value?.let { this.value = value }
        dataSource?.let { this.propertyDataSource = dataSource }
    }
            .apply(init)
            .addTo(container)
}

fun ComponentContainer.textField(caption: String? = null,
                                 value: String? = null,
                                 dataSource: Property<out Any>? = null,
                                 init: TextField.() -> Unit = {}) = TextField()
        .process(this, caption, value, dataSource, init)

fun ComponentContainer.textArea(caption: String? = null,
                                value: String? = null,
                                dataSource: Property<out Any>? = null,
                                init: TextArea.() -> Unit = {}) = TextArea()
        .process(this, caption, value, dataSource, init)

fun ComponentContainer.richTextArea(caption: String? = null,
                                    value: String? = null,
                                    dataSource: Property<out Any>? = null,
                                    init: RichTextArea.() -> Unit = {}) = RichTextArea()
        .process(this, caption, value, dataSource, init)

fun ComponentContainer.passwordField(caption: String? = null,
                                     value: String? = null,
                                     dataSource: Property<out Any>? = null,
                                     init: PasswordField.() -> Unit = {}) = PasswordField()
        .process(this, caption, value, dataSource, init)

fun ComponentContainer.dateField(caption: String? = null,
                                 value: Date? = null,
                                 dataSource: Property<out Any>? = null,
                                 init: DateField.() -> Unit = {}) = DateField()
        .process(this, caption, value, dataSource, init)

fun ComponentContainer.inlineDateField(caption: String? = null,
                                       value: Date? = null,
                                       dataSource: Property<out Any>? = null,
                                       init: InlineDateField.() -> Unit = {}) = InlineDateField()
        .process(this, caption, value, dataSource, init)

fun ComponentContainer.calendar(caption: String? = null,
                                eventProvider: CalendarEventProvider = BasicEventProvider(),
                                init: Calendar.() -> Unit = {}): Calendar {
    return Calendar()
            .apply {
                caption?.let { this.caption = caption }
                this.eventProvider = eventProvider
            }
            .apply(init)
            .addTo(this)
}
