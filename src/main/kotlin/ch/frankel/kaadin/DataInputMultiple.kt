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

private fun <S : AbstractSelect> S.process(container: ComponentContainer,
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

fun ComponentContainer.comboBox(caption: String? = null,
                                options: Collection<Any> = emptyList(),
                                dataSource: Container = IndexedContainer(),
                                init: ComboBox.() -> Unit = {}) = ComboBox()
        .process(this, caption, options, dataSource, init)

fun ComponentContainer.nativeSelect(caption: String? = null,
                                    options: Collection<Any> = emptyList(),
                                    dataSource: Container = IndexedContainer(),
                                    init: NativeSelect.() -> Unit = {}) = NativeSelect()
        .process(this, caption, options, dataSource, init)

fun ComponentContainer.listSelect(caption: String? = null,
                                  options: Collection<Any> = emptyList(),
                                  dataSource: Container = IndexedContainer(),
                                  init: ListSelect.() -> Unit = {}) = ListSelect()
        .process(this, caption, options, dataSource, init)

fun ComponentContainer.twinColSelect(caption: String? = null,
                                     dataSource: Container = IndexedContainer(),
                                     init: TwinColSelect.() -> Unit = {}) = TwinColSelect()
        .process(this, caption, emptyList(), dataSource, init)

fun ComponentContainer.optionGroup(caption: String? = null,
                                   options: Collection<Any> = emptyList(),
                                   dataSource: Container = IndexedContainer(),
                                   init: OptionGroup.() -> Unit = {}) = OptionGroup()
        .process(this, caption, emptyList(), dataSource, init)