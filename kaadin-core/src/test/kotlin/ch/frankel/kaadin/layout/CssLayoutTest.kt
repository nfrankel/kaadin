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

class CssLayoutTest {

    @Test
    fun `css layout should be added to layout`() {
        val layout = horizontalLayout {
            cssLayout()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(CssLayout::class.java)
    }

    @Test(dependsOnMethods = arrayOf("css layout should be added to layout"))
    fun `css layout should accept one child component`() {
        val layout = cssLayout {
            label()
        }
        assertThat(layout.componentCount).isEqualTo(1)
    }

    @Test(dependsOnMethods = arrayOf("css layout should accept one child component"))
    fun `css layout should accept many child components`() {
        val layout = cssLayout {
            IntRange(0, 9).forEach {
                label()
            }
        }
        assertThat(layout.componentCount).isEqualTo(10)
    }
}