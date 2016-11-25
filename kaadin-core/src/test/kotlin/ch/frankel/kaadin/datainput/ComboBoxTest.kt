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

class ComboBoxTest {

    @Test
    fun `combobox should be added to layout`() {
        val layout = horizontalLayout {
            comboBox()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(ComboBox::class.java)
    }

    @Test(dependsOnMethods = arrayOf("combobox should be added to layout"))
    fun `combobox elements can be initialized via varargs`() {
        val layout = horizontalLayout {
            comboBox("caption", "One", "Two", "Three")
        }
        val component = layout.getComponent(0) as ComboBox
        assertThat(component.size()).isEqualTo(3)
    }

    @Test(dependsOnMethods = arrayOf("combobox should be added to layout"))
    fun `combobox elements can be initialized via collection`() {
        val layout = horizontalLayout {
            comboBox(options = arrayListOf("One", "Two", "Three"))
        }
        val component = layout.getComponent(0) as ComboBox
        assertThat(component.size()).isEqualTo(3)
    }

    @Test(dependsOnMethods = arrayOf("combobox should be added to layout"))
    fun `combobox elements can be initialized via property`() {
        val container = BeanItemContainer(String::class.java).apply {
            addAll(arrayListOf("One", "Two", "Three"))
        }
        val layout = horizontalLayout {
            comboBox(dataSource = container)
        }
        val component = layout.getComponent(0) as ComboBox
        assertThat(component.size()).isEqualTo(3)
    }

    @Test(dependsOnMethods = arrayOf("combobox should be added to layout"))
    fun `combobox caption can be initialized`() {
        val caption = "caption"
        val layout = horizontalLayout {
            comboBox(caption)
        }
        val component = layout.getComponent(0) as ComboBox
        assertThat(component.caption).isEqualTo(caption)
    }

    @Test(dependsOnMethods = arrayOf("combobox should be added to layout"))
    fun `combobox should be configurable`() {
        val layout = horizontalLayout {
            comboBox {
                allowTextInput()
                allowNewItems()
                allowNullSelection()
            }
        }
        val component = layout.getComponent(0) as ComboBox
        assertThat(component.isTextInputAllowed).isTrue()
        assertThat(component.isNewItemsAllowed).isTrue()
        assertThat(component.isNullSelectionAllowed).isTrue()
    }

    @Test(dependsOnMethods = arrayOf("combobox should be added to layout"))
    fun `combobox value change listener can be initialized`() {
        val data = "dummy"
        val layout = horizontalLayout {
            comboBox(onValueChange = { this.data = data })
        }
        val component = layout.getComponent(0) as ComboBox
        val id = component.addItem()
        component.select(id)
        assertThat(layout.data).isEqualTo(data)
    }

    @Test(dependsOnMethods = arrayOf("combobox elements can be initialized via varargs", "combobox should be configurable"))
    fun `combobox item caption can be set`() {
        val layout = horizontalLayout {
            comboBox("caption", "One", "Two", "Three") {
                itemCaption("One", "A")
                itemCaption("Two", "B")
                itemCaption("Three", "C")
                unsetItemCaption("One")
            }
        }
        val component = layout.getComponent(0) as ComboBox
        assertThat(component.getItemCaption("One")).isEqualTo("One")
        assertThat(component.getItemCaption("Two")).isEqualTo("B")
        assertThat(component.getItemCaption("Three")).isEqualTo("C")
    }
}