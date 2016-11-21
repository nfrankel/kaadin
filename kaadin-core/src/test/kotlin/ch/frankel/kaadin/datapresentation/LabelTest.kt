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
package ch.frankel.kaadin.datapresentation

import ch.frankel.kaadin.*
import com.vaadin.data.util.*
import com.vaadin.shared.ui.label.ContentMode.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class LabelTest {

    @Test
    fun `label should be added to layout`() {
        val layout = horizontalLayout {
            label()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(Label::class.java)
    }

    @Test(dependsOnMethods = arrayOf("label should be added to layout"))
    fun `label can be initialized with text`() {
        val text = "Hello world"
        val layout = horizontalLayout {
            label(text)
        }
        val component = layout.getComponent(0) as Label
        assertThat(component.value).isEqualTo(text)
    }

    @Test
    fun `html label should be added to layout`() {
        val layout = horizontalLayout {
            html()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(Label::class.java)
    }

    @Test(dependsOnMethods = arrayOf("html label should be added to layout"))
    fun `html label should be added be of type html`() {
        val layout = horizontalLayout {
            html()
        }
        val component = layout.getComponent(0) as Label
        assertThat(component.contentMode).isSameAs(HTML)
    }

    @Test(dependsOnMethods = arrayOf("html label should be added to layout"))
    fun `html label should be initialized with property`() {
        val text = "Hello world"
        val property = ObjectProperty(text)
        val layout = horizontalLayout {
            html(property)
        }
        val component = layout.getComponent(0) as Label
        assertThat(component.value).isEqualTo(text)
    }

    @Test(dependsOnMethods = arrayOf("html label should be added to layout"))
    fun `html label should be configurable`() {
        val text = "Hello world"
        val layout = horizontalLayout {
            html {
                value = text
            }
        }
        val component = layout.getComponent(0) as Label
        assertThat(component.value).isEqualTo(text)
    }
}