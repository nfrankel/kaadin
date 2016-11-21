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
import com.vaadin.shared.ui.colorpicker.Color.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class ColorPickerTest {

    @Test
    fun `color picker should be added to layout`() {
        val layout = horizontalLayout {
            colorPicker()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(ColorPicker::class.java)
    }

    @Test(dependsOnMethods = arrayOf("color picker should be added to layout"))
    fun `color picker caption can be configured`() {
        val caption = "caption"
        val layout = horizontalLayout {
            colorPicker(caption)
        }
        val component = layout.getComponent(0) as ColorPicker
        assertThat(component).hasFieldOrPropertyWithValue("caption", caption)
    }

    @Test(dependsOnMethods = arrayOf("color picker should be added to layout"))
    fun `color picker color can be configured`() {
        val layout = horizontalLayout {
            colorPicker(initialColor = BLACK)
        }
        val component = layout.getComponent(0) as ColorPicker
        assertThat(component).hasFieldOrPropertyWithValue("color", BLACK)
    }

    @Test(dependsOnMethods = arrayOf("color picker should be added to layout"))
    fun `color picker should be configurable`() {
        val layout = horizontalLayout {
            colorPicker {
                color = BLUE
            }
        }
        val component = layout.getComponent(0) as ColorPicker
        assertThat(component).hasFieldOrPropertyWithValue("color", BLUE)
    }
}