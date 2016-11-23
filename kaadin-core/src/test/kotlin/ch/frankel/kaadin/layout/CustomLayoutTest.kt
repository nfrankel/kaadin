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
package ch.frankel.kaadin.layout

import ch.frankel.kaadin.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class CustomLayoutTest {

    @Test
    fun `custom layout should be added to layout`() {
        val layout = horizontalLayout {
            customLayout()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(CustomLayout::class.java)
    }

    @Test(dependsOnMethods = arrayOf("custom layout should be added to layout"))
    fun `custom layout name can be initialized`() {
        val name = "a name"
        val layout = horizontalLayout {
            customLayout(name)
        }
        val component = layout.getComponent(0) as CustomLayout
        assertThat(component.templateName).isEqualTo(name)
    }

    @Test(dependsOnMethods = arrayOf("custom layout should be added to layout"))
    fun `custom layout content can be initialized`() {
        val stream = javaClass.getResourceAsStream("/template.html")
        val layout = horizontalLayout {
            customLayout(stream)
        }
        val component = layout.getComponent(0) as CustomLayout
        assertThat(component.templateContents).isEqualTo("<html><body><div id=\"slot\"></div></body></html>")
    }

    @Test(dependsOnMethods = arrayOf("custom layout should be added to layout"))
    fun `default custom layout should accept one child component`() {
        val layout = customLayout {
            on("aslot") set Label()
        }
        assertThat(layout.componentCount).isEqualTo(1)
    }

    @Test(dependsOnMethods = arrayOf("default custom layout should accept one child component"))
    fun `custom layout should accept many child components`() {
        val layout = customLayout {
            IntRange(0, 9).forEach {
                on("slot$it") set Label()
            }
        }
        assertThat(layout.componentCount).isEqualTo(10)
    }
}