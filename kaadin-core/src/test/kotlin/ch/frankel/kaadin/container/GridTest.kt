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
package ch.frankel.kaadin.container

import ch.frankel.kaadin.beanItemContainer
import com.vaadin.data.util.*
import com.vaadin.ui.*
import com.vaadin.ui.Grid.SelectionMode.*
import com.vaadin.ui.Grid.SelectionModel.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.*

class GridTest {

    lateinit var grid: Grid

    @BeforeMethod
    private fun setUp() {
        grid = Grid()
    }

    @Test
    fun `bean item container can be added to grid`() {
        grid.beanItemContainer(String::class.java)
        assertThat(grid.containerDataSource).isNotNull().isInstanceOf(BeanItemContainer::class.java)
    }

    @Test(dependsOnMethods = arrayOf("bean item container can be added to grid"))
    fun `bean item container can be initialized with collection`() {
        val items = arrayListOf("One", "Two", "Three")
        grid.beanItemContainer(String::class.java, items)
        assertThat(grid.containerDataSource).isNotNull().isInstanceOf(BeanItemContainer::class.java)
        grid.setSelectionMode(MULTI)
        val selection = grid.selectionModel as Multi
        selection.selectAll()
        assertThat(grid.selectedRows.size).isEqualTo(items.size)
    }

    @Test(dependsOnMethods = arrayOf("bean item container can be added to grid"))
    fun `bean item container can be initialized with varargs`() {
        val items = arrayOf("One", "Two", "Three")
        grid.beanItemContainer(String::class.java, * items)
        assertThat(grid.containerDataSource).isNotNull().isInstanceOf(BeanItemContainer::class.java)
        grid.setSelectionMode(MULTI)
        val selection = grid.selectionModel as Multi
        selection.selectAll()
        assertThat(grid.selectedRows.size).isEqualTo(items.size)
    }
}