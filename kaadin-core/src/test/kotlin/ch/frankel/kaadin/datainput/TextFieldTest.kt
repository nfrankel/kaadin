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

class TextFieldTest {

    @Test
    fun `text should be added to layout`() {
        val layout = horizontalLayout {
            textField()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull.isInstanceOf(TextField::class.java)
    }

    @Test(dependsOnMethods = ["text should be added to layout"])
    fun `text value can be initialized`() {
        val text = "Hello world"
        val layout = horizontalLayout {
            textField(value = text)
        }
        val component = layout.getComponent(0) as TextField
        assertThat(component.value).isEqualTo(text)
    }

    @Test(dependsOnMethods = ["text should be added to layout"])
    fun `text caption can be initialized`() {
        val caption = "Hello world"
        val layout = horizontalLayout {
            textField(caption)
        }
        val component = layout.getComponent(0) as TextField
        assertThat(component.caption).isEqualTo(caption)
    }

    @Test(dependsOnMethods = ["text should be added to layout"])
    fun `text value can be initialized via property`() {
        val text = "Hello world"
        val property = ObjectProperty(text)
        val layout = horizontalLayout {
            textField(dataSource = property)
        }
        val component = layout.getComponent(0) as TextField
        assertThat(component.value).isEqualTo(text)
    }

    @Test(dependsOnMethods = ["text should be added to layout"])
    fun `text should be configurable`() {
        val text = "Hello world"
        val layout = horizontalLayout {
            textField {
                value = text
            }
        }
        val component = layout.getComponent(0) as TextField
        assertThat(component.value).isEqualTo(text)
    }
}