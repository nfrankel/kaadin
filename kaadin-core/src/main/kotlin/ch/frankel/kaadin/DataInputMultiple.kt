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
import com.vaadin.server.*
import com.vaadin.ui.*

/**
 * see http://demo.vaadin.com/sampler/#ui/data-input/multiple-value
 */
private fun <S : AbstractSelect> S.process(container: HasComponents,
                                           caption: String?,
                                           options: Collection<Any>,
                                           dataSource: Container,
                                           onValueChange: (event: Property.ValueChangeEvent) -> Unit,
                                           init: S.() -> Unit): S {
    return apply {
        caption?.let { this.caption = caption }
        options.forEach { dataSource.addItem(it) }
        addValueChangeListener(onValueChange)
        this.containerDataSource = dataSource
    }
            .apply(init)
            .addTo(container)
}

fun AbstractSelect.allowNewItems() {
    isNewItemsAllowed = true
}
fun AbstractSelect.disallowNewItems() {
    isNewItemsAllowed = false
}
fun AbstractSelect.allowNullSelection() {
    isNullSelectionAllowed = true
}
fun AbstractSelect.disallowNullSelection() {
    isNullSelectionAllowed = false
}
fun AbstractSelect.selectSingle() {
    isMultiSelect = false
}
fun AbstractSelect.selectMulti() {
    isMultiSelect = true
}
fun AbstractSelect.itemCaption(itemId: Any, caption: String) = setItemCaption(itemId, caption)
fun AbstractSelect.unsetItemCaption(itemId: Any) = setItemCaption(itemId, null)
fun AbstractSelect.itemIcon(itemId: Any, icon: Resource) = setItemIcon(itemId, icon)
fun AbstractSelect.unsetItemIcon(itemId: Any) = setItemIcon(itemId, null)

fun HasComponents.comboBox(caption: String? = null,
                           vararg options: Any,
                           onValueChange: (event: Property.ValueChangeEvent) -> Unit = {},
                           init: ComboBox.() -> Unit = {}) = comboBox(caption, options.toList(), onValueChange, init)

fun HasComponents.comboBox(caption: String? = null,
                           options: Collection<Any> = emptyList(),
                           onValueChange: (event: Property.ValueChangeEvent) -> Unit = {},
                           init: ComboBox.() -> Unit = {}) = ComboBox()
        .process(this, caption, options, IndexedContainer(), onValueChange, init)

fun HasComponents.comboBox(caption: String? = null,
                           dataSource: Container,
                           onValueChange: (event: Property.ValueChangeEvent) -> Unit = {},
                           init: ComboBox.() -> Unit = {}) = ComboBox()
        .process(this, caption, emptyList(), dataSource, onValueChange, init)

fun ComboBox.allowTextInput() {
    isTextInputAllowed = true
}
fun ComboBox.disallowTextInput() {
    isTextInputAllowed = false
}

fun HasComponents.nativeSelect(caption: String? = null,
                               vararg options: Any,
                               onValueChange: (event: Property.ValueChangeEvent) -> Unit = {},
                               init: NativeSelect.() -> Unit = {}) = nativeSelect(caption, options.toList(), onValueChange, init)

fun HasComponents.nativeSelect(caption: String? = null,
                               options: Collection<Any> = emptyList(),
                               onValueChange: (event: Property.ValueChangeEvent) -> Unit = {},
                               init: NativeSelect.() -> Unit = {}) = NativeSelect()
        .process(this, caption, options, IndexedContainer(), onValueChange, init)

fun HasComponents.nativeSelect(caption: String? = null,
                               dataSource: Container,
                               onValueChange: (event: Property.ValueChangeEvent) -> Unit = {},
                               init: NativeSelect.() -> Unit = {}) = NativeSelect()
        .process(this, caption, emptyList(), dataSource, onValueChange, init)

fun HasComponents.listSelect(caption: String? = null,
                             vararg options: Any,
                             onValueChange: (event: Property.ValueChangeEvent) -> Unit = {},
                             init: ListSelect.() -> Unit = {}) = listSelect(caption, options.toList(), onValueChange, init)

fun HasComponents.listSelect(caption: String? = null,
                             options: Collection<Any> = emptyList(),
                             onValueChange: (event: Property.ValueChangeEvent) -> Unit = {},
                             init: ListSelect.() -> Unit = {}) = ListSelect()
        .process(this, caption, options, IndexedContainer(), onValueChange, init)

fun HasComponents.listSelect(caption: String? = null,
                             dataSource: Container,
                             onValueChange: (event: Property.ValueChangeEvent) -> Unit = {},
                             init: ListSelect.() -> Unit = {}) = ListSelect()
        .process(this, caption, emptyList(), dataSource, onValueChange, init)

fun HasComponents.twinColSelect(caption: String? = null,
                                vararg options: Any,
                                onValueChange: (event: Property.ValueChangeEvent) -> Unit = {},
                                init: TwinColSelect.() -> Unit = {}) = twinColSelect(caption, options.toList(), onValueChange, init)

fun HasComponents.twinColSelect(caption: String? = null,
                                options: Collection<Any> = emptyList(),
                                onValueChange: (event: Property.ValueChangeEvent) -> Unit = {},
                                init: TwinColSelect.() -> Unit = {}) = TwinColSelect()
        .process(this, caption, options, IndexedContainer(), onValueChange, init)

fun HasComponents.twinColSelect(caption: String? = null,
                                dataSource: Container,
                                onValueChange: (event: Property.ValueChangeEvent) -> Unit = {},
                                init: TwinColSelect.() -> Unit = {}) = TwinColSelect()
        .process(this, caption, emptyList(), dataSource, onValueChange, init)

fun HasComponents.optionGroup(caption: String? = null,
                              options: Collection<Any> = emptyList(),
                              onValueChange: (event: Property.ValueChangeEvent) -> Unit = {},
                              init: OptionGroup.() -> Unit = {}) = OptionGroup()
        .process(this, caption, options, IndexedContainer(), onValueChange, init)

fun HasComponents.optionGroup(caption: String? = null,
                              vararg options: String,
                              onValueChange: (event: Property.ValueChangeEvent) -> Unit = {},
                              init: OptionGroup.() -> Unit = {}) = optionGroup(caption, options.toList(), onValueChange, init)

fun HasComponents.optionGroup(caption: String? = null,
                              dataSource: Container,
                              onValueChange: (event: Property.ValueChangeEvent) -> Unit = {},
                              init: OptionGroup.() -> Unit = {}) = OptionGroup()
        .process(this, caption, emptyList(), dataSource, onValueChange, init)

fun OptionGroup.allowHtml() {
    isHtmlContentAllowed = true
}
fun OptionGroup.disallowHtml() {
    isHtmlContentAllowed = false
}
fun OptionGroup.enableItem(itemId: Any) = setItemEnabled(itemId, true)
fun OptionGroup.disableItem(itemId: Any) = setItemEnabled(itemId, false)