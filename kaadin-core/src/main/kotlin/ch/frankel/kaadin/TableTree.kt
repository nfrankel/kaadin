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
 * see http://demo.vaadin.com/sampler/#ui/grids-and-trees
 */
private fun <S : AbstractSelect> S.process(container: HasComponents,
                                           caption: String?,
                                           dataSource: Container?,
                                           init: S.() -> Unit): S {
    return apply {
        caption?.let { this.caption = caption }
        dataSource?.let { this.containerDataSource = dataSource }
    }
            .apply(init)
            .addTo(container)
}

fun HasComponents.table(caption: String? = null,
                        dataSource: Container? = null,
                        init: Table.() -> Unit = {}) = Table()
        .process(this, caption, dataSource, init)

fun HasComponents.tree(caption: String? = null,
                       dataSource: Container = HierarchicalContainer(),
                       init: Tree.() -> Unit = {}) = Tree()
        .process(this, caption, dataSource, init)

fun HasComponents.treeTable(caption: String? = null,
                            dataSource: Container = HierarchicalContainer(),
                            init: TreeTable.() -> Unit = {}) = TreeTable()
        .process(this, caption, dataSource, init)

fun <T> beanItemContainer(type: Class<T>, init: BeanItemContainer<T>.() -> Unit = {}): BeanItemContainer<T> = BeanItemContainer<T>(type)
        .apply(init)
fun <T> beanItemContainer(type: Class<T>, collection: Collection<T>, init: BeanItemContainer<T>.() -> Unit = {}) =
        beanItemContainer(type, init)
        .apply {
            beans(collection)
        }

fun <T> BeanItemContainer<T>.bean(bean: T, init: BeanItem<T>.() -> Unit = {}) = addBean(bean).apply(init)
fun <T> BeanItemContainer<T>.beanAfter(bean: T, previousBean: T, init: BeanItem<T>.() -> Unit = {}) = addItemAfter(bean, previousBean).apply(init)
fun <T> BeanItemContainer<T>.beanAt(bean: T, index: Int, init: BeanItem<T>.() -> Unit = {}) = addItemAt(index, bean).apply(init)
fun <T> BeanItemContainer<T>.beans(collection: Collection<T>, init: BeanItem<T>.() -> Unit = {}) = collection.forEach { bean(it, init) }
fun <T> BeanItemContainer<T>.beans(vararg  bean: T, init: BeanItem<T>.() -> Unit = {}) = bean.forEach { bean(it, init) }

