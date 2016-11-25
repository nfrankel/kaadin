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
package ch.frankel.kaadin.datainput

import ch.frankel.kaadin.*
import com.vaadin.data.util.*
import com.vaadin.server.*
import com.vaadin.server.FontAwesome.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class ListSelectTest {

    @Test
    fun `list select should be added to layout`() {
        val layout = horizontalLayout {
            listSelect()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(ListSelect::class.java)
    }

    @Test(dependsOnMethods = arrayOf("list select should be added to layout"))
    fun `list select elements can be initialized via varargs`() {
        val layout = horizontalLayout {
            listSelect("caption", "One", "Two", "Three")
        }
        val component = layout.getComponent(0) as ListSelect
        assertThat(component.size()).isEqualTo(3)
    }

    @Test(dependsOnMethods = arrayOf("list select should be added to layout"))
    fun `list select elements can be initialized via collection`() {
        val layout = horizontalLayout {
            listSelect(options = arrayListOf("One", "Two", "Three"))
        }
        val component = layout.getComponent(0) as ListSelect
        assertThat(component.size()).isEqualTo(3)
    }

    @Test(dependsOnMethods = arrayOf("list select should be added to layout"))
    fun `list select elements can be initialized via property`() {
        val container = BeanItemContainer(String::class.java).apply {
            addAll(arrayListOf("One", "Two", "Three"))
        }
        val layout = horizontalLayout {
            listSelect(dataSource = container)
        }
        val component = layout.getComponent(0) as ListSelect
        assertThat(component.size()).isEqualTo(3)
    }

    @Test(dependsOnMethods = arrayOf("list select should be added to layout"))
    fun `list select caption can be initialized`() {
        val caption = "caption"
        val layout = horizontalLayout {
            listSelect(caption)
        }
        val component = layout.getComponent(0) as ListSelect
        assertThat(component.caption).isEqualTo(caption)
    }

    @Test(dependsOnMethods = arrayOf("list select should be added to layout"))
    fun `list select should be configurable`() {
        val layout = horizontalLayout {
            listSelect {
                selectMulti()
                selectSingle()
            }
        }
        val component = layout.getComponent(0) as ListSelect
        assertThat(component.isMultiSelect).isFalse()
    }

    @Test(dependsOnMethods = arrayOf("list select should be added to layout"))
    fun `list select value change listener can be initialized`() {
        val tag = "dummy"
        val layout = horizontalLayout {
            listSelect(onValueChange = { this.id = tag })
        }
        val component = layout.getComponent(0) as ListSelect
        val id = component.addItem()
        component.select(id)
        assertThat(layout.id).isEqualTo(tag)
    }

    @Test(dependsOnMethods = arrayOf("list select elements can be initialized via varargs", "list select should be configurable"))
    fun `list select item icon can be set`() {
        val layout = horizontalLayout {
            listSelect("caption", "One", "Two", "Three") {
                itemIcon("One", ALIGN_LEFT)
                itemIcon("Two", ALIGN_CENTER)
                itemIcon("Three", ALIGN_RIGHT)
                unsetItemIcon("Two")
            }
        }
        val component = layout.getComponent(0) as ListSelect
        assertThat(component.getItemIcon("One")).isSameAs(ALIGN_LEFT)
        assertThat(component.getItemIcon("Two")).isNull()
        assertThat(component.getItemIcon("Three")).isSameAs(ALIGN_RIGHT)
    }
}