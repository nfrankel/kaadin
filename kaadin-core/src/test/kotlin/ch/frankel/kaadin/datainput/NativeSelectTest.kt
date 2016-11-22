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
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class NativeSelectTest {

    @Test
    fun `native select should be added to layout`() {
        val layout = horizontalLayout {
            nativeSelect()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(NativeSelect::class.java)
    }

    @Test(dependsOnMethods = arrayOf("native select should be added to layout"))
    fun `native select elements can be initialized via varargs`() {
        val layout = horizontalLayout {
            nativeSelect("caption", "One", "Two", "Three")
        }
        val component = layout.getComponent(0) as NativeSelect
        assertThat(component.size()).isEqualTo(3)
    }

    @Test(dependsOnMethods = arrayOf("native select should be added to layout"))
    fun `native select elements can be initialized via collection`() {
        val layout = horizontalLayout {
            nativeSelect(options = arrayListOf("One", "Two", "Three"))
        }
        val component = layout.getComponent(0) as NativeSelect
        assertThat(component.size()).isEqualTo(3)
    }

    @Test(dependsOnMethods = arrayOf("native select should be added to layout"))
    fun `native select elements can be initialized via property`() {
        val container = BeanItemContainer(String::class.java).apply {
            addAll(arrayListOf("One", "Two", "Three"))
        }
        val layout = horizontalLayout {
            nativeSelect(dataSource = container)
        }
        val component = layout.getComponent(0) as NativeSelect
        assertThat(component.size()).isEqualTo(3)
    }

    @Test(dependsOnMethods = arrayOf("native select should be added to layout"))
    fun `native select caption can be initialized`() {
        val caption = "caption"
        val layout = horizontalLayout {
            nativeSelect(caption)
        }
        val component = layout.getComponent(0) as NativeSelect
        assertThat(component.caption).isEqualTo(caption)
    }

    @Test(dependsOnMethods = arrayOf("native select should be added to layout"))
    fun `native select should be configurable`() {
        val layout = horizontalLayout {
            nativeSelect {
                disallowNewItems()
                disallowNullSelection()
            }
        }
        val component = layout.getComponent(0) as NativeSelect
        assertThat(component.isNewItemsAllowed).isFalse()
        assertThat(component.isNullSelectionAllowed).isFalse()
    }

    @Test(dependsOnMethods = arrayOf("native select should be added to layout"))
    fun `native select value change listener can be initialized`() {
        val data = "dummy"
        val layout = horizontalLayout {
            nativeSelect(onValueChange = { this.data = data })
        }
        val component = layout.getComponent(0) as NativeSelect
        val id = component.addItem()
        component.select(id)
        assertThat(layout.data).isEqualTo(data)
    }
}