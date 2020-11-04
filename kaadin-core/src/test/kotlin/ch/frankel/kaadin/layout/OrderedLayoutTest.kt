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

class OrderedLayoutTest {

    @Test
    fun `vertical layout should be added to layout`() {
        val layout = horizontalLayout {
            verticalLayout()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull.isInstanceOf(VerticalLayout::class.java)
    }

    @Test
    fun `horizontal layout should be added to layout`() {
        val layout = horizontalLayout {
            horizontalLayout()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull.isInstanceOf(HorizontalLayout::class.java)
    }

    @Test
    fun `form layout should be added to layout`() {
        val layout = horizontalLayout {
            formLayout()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull.isInstanceOf(FormLayout::class.java)
    }

    @Test
    fun `vertical layout spacing can be initialized`() {
        val layout = verticalLayout(spacing = true, margin = true)
        assertThat(layout.isSpacing).isTrue
        assertThat(layout.margin).isNotNull
        assertThat(layout.margin.hasBottom()).isTrue
        assertThat(layout.margin.hasTop()).isTrue
        assertThat(layout.margin.hasLeft()).isTrue
        assertThat(layout.margin.hasRight()).isTrue
    }

    @Test
    fun `horizontal layout should accept one child component`() {
        val layout = horizontalLayout {
            label()
        }
        assertThat(layout.componentCount).isEqualTo(1)
    }

    @Test
    fun `form layout should accept many child components`() {
        val layout = formLayout {
            repeat(10) {
                label()
            }
        }
        assertThat(layout.componentCount).isEqualTo(10)
    }
}