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

/**
 * see http://demo.vaadin.com/sampler/#ui/data-input/text-input
 * see http://demo.vaadin.com/sampler/#ui/data-input/dates
 */
private fun <F : AbstractField<T>, T : Any> F.process(container: HasComponents,
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

fun HasComponents.textField(caption: String? = null,
                            value: String = "",
                            init: TextField.() -> Unit = {}) = TextField()
        .process(this, caption, value, null, init)

fun HasComponents.textField(caption: String? = null,
                            dataSource: Property<String>,
                            init: TextField.() -> Unit = {}) = TextField()
        .process(this, caption, null, dataSource, init)

fun HasComponents.textArea(caption: String? = null,
                           value: String? = "",
                           init: TextArea.() -> Unit = {}) = TextArea()
        .process(this, caption, value, null, init)

fun HasComponents.textArea(caption: String? = null,
                           dataSource: Property<String>,
                           init: TextArea.() -> Unit = {}) = TextArea()
        .process(this, caption, null, dataSource, init)

fun HasComponents.richTextArea(caption: String? = null,
                               value: String = "",
                               init: RichTextArea.() -> Unit = {}) = RichTextArea()
        .process(this, caption, value, null, init)

fun HasComponents.richTextArea(caption: String? = null,
                               dataSource: Property<String>,
                               init: RichTextArea.() -> Unit = {}) = RichTextArea()
        .process(this, caption, null, dataSource, init)

fun HasComponents.passwordField(caption: String? = null,
                                value: String? = "",
                                init: PasswordField.() -> Unit = {}) = PasswordField()
        .process(this, caption, value, null, init)

fun HasComponents.passwordField(caption: String? = null,
                                dataSource: Property<String>,
                                init: PasswordField.() -> Unit = {}) = PasswordField()
        .process(this, caption, null, dataSource, init)

fun HasComponents.dateField(caption: String? = null,
                            value: Date? = null,
                            init: DateField.() -> Unit = {}) = DateField()
        .process(this, caption, value, null, init)

fun HasComponents.dateField(caption: String? = null,
                            dataSource: Property<Date>,
                            init: DateField.() -> Unit = {}) = DateField()
        .process(this, caption, null, dataSource, init)

fun HasComponents.inlineDateField(caption: String? = null,
                                  value: Date? = null,
                                  init: InlineDateField.() -> Unit = {}) = InlineDateField()
        .process(this, caption, value, null, init)

fun HasComponents.inlineDateField(caption: String? = null,
                                  dataSource: Property<Date>,
                                  init: InlineDateField.() -> Unit = {}) = InlineDateField()
        .process(this, caption, null, dataSource, init)

fun HasComponents.calendar(caption: String? = null,
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
