package ch.frankel.kaadin

import com.vaadin.data.*
import com.vaadin.data.Container.*
import com.vaadin.data.util.*
import com.vaadin.ui.*

fun Container.item(): Any? = addItem()
fun Container.item(vararg itemId: Any, init: Item.() -> Unit = {}) = itemId.forEach { addItem(it).apply(init) }
fun Container.items(itemIds: Collection<Any>, init: Item.() -> Unit = {}) = item(itemIds.toTypedArray(), init)
fun Container.property(propertyId: Any, type: Class<*>, defaultValue: Any) = addContainerProperty(propertyId, type, defaultValue)

fun Ordered.itemAfter(previousItemId: Any) = addItemAfter(previousItemId)
fun Ordered.itemAfter(previousItemId: Any, newItemId: Any, init: Item.() -> Unit = {}) = addItemAfter(previousItemId, newItemId).apply(init)

fun Indexed.itemAt(index: Int) = addItemAt(index)
fun Indexed.itemAt(index: Int, newItemId: Any, init: Item.() -> Unit = {}) = addItemAt(index, newItemId).apply(init)

fun Hierarchical.allowChildren(itemId: Any) = setChildrenAllowed(itemId, true)
fun Hierarchical.disallowChildren(itemId: Any) = setChildrenAllowed(itemId, false)

fun SimpleFilterable.filter(propertyId: Any, filterString: String, ignoreCase: Boolean  = true, onlyMatchPrefix: Boolean = true) =
        addContainerFilter(propertyId, filterString, ignoreCase, onlyMatchPrefix)
fun Filterable.filter(filter: Filter) = addContainerFilter(filter)

// Because Grid doesn't implement Container.Viewer (it sucks)
fun <T> Grid.beanItemContainer(type: Class<T>, init: BeanItemContainer<T>.() -> Unit = {}): BeanItemContainer<T> {
    val beanItemContainer = BeanItemContainer<T>(type).apply(init)
    containerDataSource = beanItemContainer
    return beanItemContainer
}
fun <T> Grid.beanItemContainer(type: Class<T>, collection: Collection<T>, init: BeanItemContainer<T>.() -> Unit = {}) =
        beanItemContainer(type, init).beans(collection)
fun <T> Grid.beanItemContainer(type: Class<T>, vararg bean:T, init: BeanItemContainer<T>.() -> Unit = {}) =
        beanItemContainer(type, init).apply { bean.forEach { bean(it) } }

fun <T> Viewer.beanItemContainer(type: Class<T>, init: BeanItemContainer<T>.() -> Unit = {}): BeanItemContainer<T> {
        val beanItemContainer = BeanItemContainer<T>(type).apply(init)
        containerDataSource = beanItemContainer
        return beanItemContainer
    }
fun <T> Viewer.beanItemContainer(type: Class<T>, collection: Collection<T>, init: BeanItemContainer<T>.() -> Unit = {}) =
        beanItemContainer(type, init).beans(collection)
fun <T> Viewer.beanItemContainer(type: Class<T>, vararg bean:T, init: BeanItemContainer<T>.() -> Unit = {}) =
        beanItemContainer(type, init).apply { bean.forEach { bean(it) } }

fun <T> BeanItemContainer<T>.bean(vararg bean: T, init: BeanItem<T>.() -> Unit = {}) = bean.forEach { addBean(it).apply(init) }
fun <T> BeanItemContainer<T>.beans(collection: Collection<T>, init: BeanItem<T>.() -> Unit = {}) = collection.forEach { addBean(it).apply(init) }
