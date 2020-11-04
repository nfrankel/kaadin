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

class RichTextAreaTest {

    @Test
    fun `rich text area should be added to layout`() {
        val layout = horizontalLayout {
            richTextArea()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull.isInstanceOf(RichTextArea::class.java)
    }

    @Test(dependsOnMethods = ["rich text area should be added to layout"])
    fun `rich text area value can be initialized`() {
        val text = "Hello world"
        val layout = horizontalLayout {
            richTextArea(value = text)
        }
        val component = layout.getComponent(0) as RichTextArea
        assertThat(component.value).isEqualTo(text)
    }

    @Test(dependsOnMethods = ["rich text area should be added to layout"])
    fun `rich text area value can be initialized via property`() {
        val text = "Hello world"
        val property = ObjectProperty(text)
        val layout = horizontalLayout {
            richTextArea(dataSource = property)
        }
        val component = layout.getComponent(0) as RichTextArea
        assertThat(component.value).isEqualTo(text)
    }

    @Test(dependsOnMethods = ["rich text area should be added to layout"])
    fun `rich text area should be configurable`() {
        val text = "Hello world"
        val layout = horizontalLayout {
            richTextArea {
                value = text
            }
        }
        val component = layout.getComponent(0) as RichTextArea
        assertThat(component.value).isEqualTo(text)
    }
}