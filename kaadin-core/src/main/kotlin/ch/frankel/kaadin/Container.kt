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
import com.vaadin.data.Container.*
import com.vaadin.data.util.*
import com.vaadin.ui.*

fun IndexedContainer.item(): Any = addItem()
fun IndexedContainer.item(vararg itemId: Any, init: Item.() -> Unit = {}) = item(itemId.toList())
fun IndexedContainer.item(itemIds: Collection<Any>, init: Item.() -> Unit = {}) = itemIds.forEach { addItem(it).apply(init) }
fun IndexedContainer.property(propertyId: Any, type: Class<*>, defaultValue: Any) = addContainerProperty(propertyId, type, defaultValue)

fun Ordered.itemAfter(previousItemId: Any): Any = addItemAfter(previousItemId)
fun Ordered.itemAfter(previousItemId: Any, newItemId: Any): Item = addItemAfter(previousItemId, newItemId)

fun Indexed.itemAt(index: Int): Any = addItemAt(index)
fun Indexed.itemAt(index: Int, newItemId: Any): Item = addItemAt(index, newItemId)

fun Hierarchical.allowChildren(itemId: Any) = setChildrenAllowed(itemId, true)
fun Hierarchical.disallowChildren(itemId: Any) = setChildrenAllowed(itemId, false)

fun SimpleFilterable.filter(propertyId: Any, filterString: String, ignoreCase: Boolean  = true, onlyMatchPrefix: Boolean = true) =
        addContainerFilter(propertyId, filterString, ignoreCase, onlyMatchPrefix)
fun Filterable.filter(filter: Filter) = addContainerFilter(filter)

// Because Grid doesn't implement Container.Viewer (it sucks)
fun <T> Grid.beanItemContainer(type: Class<T>, init: BeanItemContainer<T>.() -> Unit = {}): BeanItemContainer<T> {
    val beanItemContainer = BeanItemContainer(type).apply(init)
    containerDataSource = beanItemContainer
    return beanItemContainer
}
fun <T> Grid.beanItemContainer(type: Class<T>, collection: Collection<T>, init: BeanItemContainer<T>.() -> Unit = {}) =
        beanItemContainer(type, init).beans(collection)
fun <T> Grid.beanItemContainer(type: Class<T>, vararg bean:T, init: BeanItemContainer<T>.() -> Unit = {}) =
        beanItemContainer(type, init).apply { bean.forEach { bean(it) } }

fun <T> Viewer.beanItemContainer(type: Class<T>, init: BeanItemContainer<T>.() -> Unit = {}): BeanItemContainer<T> {
        val beanItemContainer = BeanItemContainer(type).apply(init)
        containerDataSource = beanItemContainer
        return beanItemContainer
    }
fun <T> Viewer.beanItemContainer(type: Class<T>, collection: Collection<T>, init: BeanItemContainer<T>.() -> Unit = {}) =
        beanItemContainer(type, init).beans(collection)
fun <T> Viewer.beanItemContainer(type: Class<T>, vararg bean:T, init: BeanItemContainer<T>.() -> Unit = {}) =
        beanItemContainer(type, init).apply { bean.forEach { bean(it) } }

fun <T> BeanItemContainer<T>.bean(vararg bean: T, init: BeanItem<T>.() -> Unit = {}) = bean.forEach { addBean(it).apply(init) }
fun <T> BeanItemContainer<T>.beans(collection: Collection<T>, init: BeanItem<T>.() -> Unit = {}) = collection.forEach { addBean(it).apply(init) }
