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
 * see http://demo.vaadin.com/sampler/#ui/grids-and-trees/grid
 */
fun HasComponents.grid(caption: String? = null,
                       dataSource: Container.Indexed = IndexedContainer(),
                       init: Grid.() -> Unit = {}): Grid {
    return Grid()
            .apply {
                caption?.let { this.caption = caption }
                this.containerDataSource = dataSource
            }
            .apply(init)
            .addTo(this)
}

fun Grid.column(propertyId: Any, init: Grid.Column.() -> Unit = {}) = getColumn(propertyId).apply(init)
fun Grid.columns(propertyIds: List<Any>, init: Grid.Column.() -> Unit = {}) =
        propertyIds.forEach { column(it).apply(init) }

fun Grid.footerRowAtStart(init: Grid.FooterRow.() -> Unit = {}) = prependFooterRow().apply(init)
fun Grid.footerRowAtEnd(init: Grid.FooterRow.() -> Unit = {}) = appendFooterRow().apply(init)
fun Grid.footerRowAt(index: Int, init: Grid.FooterRow.() -> Unit = {}) = addFooterRowAt(index).apply(init)

fun Grid.headerRowAtStart(init: Grid.HeaderRow.() -> Unit = {}) = prependHeaderRow().apply(init)
fun Grid.headerRowAtEnd(init: Grid.HeaderRow.() -> Unit = {}) = appendHeaderRow().apply(init)
fun Grid.headerRowAt(index: Int, init: Grid.HeaderRow.() -> Unit = {}) = addHeaderRowAt(index).apply(init)

fun Grid.cellStyleGenerator(generator: (Grid.CellReference) -> String?) = setCellStyleGenerator(generator)
fun Grid.cellStyleGenerator(predicate: (Grid.CellReference) -> Boolean, className: String) {
    val computeClassName: (Boolean) -> String? = { if (it) className else null }
    setCellStyleGenerator { computeClassName(predicate(it)) }
}
fun Grid.selectionMode(selectionMode: Grid.SelectionMode, init: Grid.SelectionModel.() -> Unit = {}) =
    setSelectionMode(selectionMode).apply(init)

// Because StaticRow is not public
fun Grid.HeaderRow.cell(propertyId: Any, init: Grid.HeaderCell.() -> Unit = {}) = getCell(propertyId).apply(init)
fun Grid.FooterRow.cell(propertyId: Any, init: Grid.FooterCell.() -> Unit = {}) = getCell(propertyId).apply(init)