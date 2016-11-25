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

class OptionGroupTest {

    @Test
    fun `option group should be added to layout`() {
        val layout = horizontalLayout {
            optionGroup()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(OptionGroup::class.java)
    }

    @Test(dependsOnMethods = arrayOf("option group should be added to layout"))
    fun `option group elements can be initialized via varargs`() {
        val layout = horizontalLayout {
            optionGroup("caption", "One", "Two", "Three")
        }
        val component = layout.getComponent(0) as OptionGroup
        assertThat(component.size()).isEqualTo(3)
    }

    @Test(dependsOnMethods = arrayOf("option group should be added to layout"))
    fun `option group elements can be initialized via collection`() {
        val layout = horizontalLayout {
            optionGroup(options = arrayListOf("One", "Two", "Three"))
        }
        val component = layout.getComponent(0) as OptionGroup
        assertThat(component.size()).isEqualTo(3)
    }

    @Test(dependsOnMethods = arrayOf("option group should be added to layout"))
    fun `option group elements can be initialized via property`() {
        val container = BeanItemContainer(String::class.java).apply {
            addAll(arrayListOf("One", "Two", "Three"))
        }
        val layout = horizontalLayout {
            optionGroup(dataSource = container)
        }
        val component = layout.getComponent(0) as OptionGroup
        assertThat(component.size()).isEqualTo(3)
    }

    @Test(dependsOnMethods = arrayOf("option group should be added to layout"))
    fun `option group caption can be initialized`() {
        val caption = "caption"
        val layout = horizontalLayout {
            optionGroup(caption)
        }
        val component = layout.getComponent(0) as OptionGroup
        assertThat(component.caption).isEqualTo(caption)
    }

    @Test(dependsOnMethods = arrayOf("option group should be added to layout"))
    fun `option group should be configurable`() {
        val layout = horizontalLayout {
            optionGroup {
                allowHtml()
            }
        }
        val component = layout.getComponent(0) as OptionGroup
        assertThat(component.isHtmlContentAllowed).isTrue()
    }

    @Test(dependsOnMethods = arrayOf("option group elements can be initialized via varargs", "option group should be configurable"))
    fun `option group items can be disabled`() {
        val layout = horizontalLayout {
            optionGroup("caption", "One", "Two", "Three") {
                itemIds.forEach {
                    disableItem(it!!)
                }
                enableItem("One")
            }
        }
        val component = layout.getComponent(0) as OptionGroup
        assertThat(component.isItemEnabled("One")).isTrue()
        assertThat(component.isItemEnabled("Two")).isFalse()
        assertThat(component.isItemEnabled("Three")).isFalse()
    }
}