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
import com.vaadin.data.util.*
import com.vaadin.ui.*

/**
 * see http://demo.vaadin.com/sampler/#ui/data-input/multiple-value
 */
private fun <S : AbstractSelect> S.process(container: HasComponents,
                                           caption: String?,
                                           options: Collection<Any>,
                                           dataSource: Container,
                                           init: S.() -> Unit): S {
    return apply {
        caption?.let { this.caption = caption }
        options.forEach { dataSource.addItem(it) }
        this.containerDataSource = dataSource
    }
            .apply(init)
            .addTo(container)
}

fun HasComponents.comboBox(caption: String? = null,
                           options: Collection<Any> = emptyList(),
                           init: ComboBox.() -> Unit = {}) = ComboBox()
        .process(this, caption, options, IndexedContainer(), init)

fun HasComponents.comboBox(caption: String? = null,
                           dataSource: Container,
                           init: ComboBox.() -> Unit = {}) = ComboBox()
        .process(this, caption, emptyList(), dataSource, init)

fun HasComponents.nativeSelect(caption: String? = null,
                               options: Collection<Any> = emptyList(),
                               init: NativeSelect.() -> Unit = {}) = NativeSelect()
        .process(this, caption, options, IndexedContainer(), init)

fun HasComponents.nativeSelect(caption: String? = null,
                               dataSource: Container,
                               init: NativeSelect.() -> Unit = {}) = NativeSelect()
        .process(this, caption, emptyList(), dataSource, init)

fun HasComponents.listSelect(caption: String? = null,
                             options: Collection<Any> = emptyList(),
                             init: ListSelect.() -> Unit = {}) = ListSelect()
        .process(this, caption, options, IndexedContainer(), init)

fun HasComponents.listSelect(caption: String? = null,
                             dataSource: Container,
                             init: ListSelect.() -> Unit = {}) = ListSelect()
        .process(this, caption, emptyList(), dataSource, init)

fun HasComponents.twinColSelect(caption: String? = null,
                                options: Collection<Any> = emptyList(),
                                init: TwinColSelect.() -> Unit = {}) = TwinColSelect()
        .process(this, caption, options, IndexedContainer(), init)

fun HasComponents.twinColSelect(caption: String? = null,
                                dataSource: Container,
                                init: TwinColSelect.() -> Unit = {}) = TwinColSelect()
        .process(this, caption, emptyList(), dataSource, init)

fun HasComponents.optionGroup(caption: String? = null,
                              options: Collection<Any> = emptyList(),
                              init: OptionGroup.() -> Unit = {}) = OptionGroup()
        .process(this, caption, options, IndexedContainer(), init)


fun HasComponents.optionGroup(caption: String? = null,
                              dataSource: Container,
                              init: OptionGroup.() -> Unit = {}) = OptionGroup()
        .process(this, caption, emptyList(), dataSource, init)