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

import ch.frankel.kaadin.checkBox
import ch.frankel.kaadin.horizontalLayout
import com.vaadin.data.util.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class CheckBoxTest {

    @Test
    fun `checkbox should be added to layout`() {
        val layout = horizontalLayout {
            checkBox()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(CheckBox::class.java)
    }

    @Test(dependsOnMethods = arrayOf("checkbox should be added to layout"))
    fun `checkbox caption can be initialized `() {
        val caption = "caption"
        val layout = horizontalLayout {
            checkBox(caption)
        }
        val component = layout.getComponent(0) as CheckBox
        assertThat(component).hasFieldOrPropertyWithValue("caption", caption)
    }

    @Test(dependsOnMethods = arrayOf("checkbox should be added to layout"))
    fun `checkbox state can be initialized through boolean`() {
        val layout = horizontalLayout {
            checkBox(value = true)
        }
        val component = layout.getComponent(0) as CheckBox
        assertThat(component).hasFieldOrPropertyWithValue("value", true)
    }

    @Test(dependsOnMethods = arrayOf("checkbox should be added to layout"))
    fun `checkbox state can be initialized through property`() {
        val property = ObjectProperty<Boolean>(true)
        val layout = horizontalLayout {
            checkBox(dataSource = property)
        }
        val component = layout.getComponent(0) as CheckBox
        assertThat(component).hasFieldOrPropertyWithValue("value", true)
    }

    @Test(dependsOnMethods = arrayOf("checkbox should be added to layout"))
    fun `checkbox should be configurable`() {
        val id = "id"
        val layout = horizontalLayout {
            checkBox {
                this.id = id
            }
        }
        val component = layout.getComponent(0) as CheckBox
        assertThat(component).hasFieldOrPropertyWithValue("id", id)
    }
}