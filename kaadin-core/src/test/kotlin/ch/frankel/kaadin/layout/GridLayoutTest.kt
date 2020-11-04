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

class GridLayoutTest {

    @Test
    fun `grid layout should be added to layout`() {
        val layout = horizontalLayout {
            gridLayout()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull.isInstanceOf(GridLayout::class.java)
    }

    @Test(dependsOnMethods = ["grid layout should be added to layout"])
    fun `grid layout rows and columns can be initialized`() {
        val layout = gridLayout(2, 3)
        assertThat(layout.columns).isEqualTo(2)
        assertThat(layout.rows).isEqualTo(3)
    }

    @Test(dependsOnMethods = ["grid layout should be added to layout"])
    fun `grid layout spacing and margin can be initialized`() {
        val layout = gridLayout(spacing = true, margin = true)
        assertThat(layout.isSpacing).isTrue
        assertThat(layout.margin).isNotNull
        assertThat(layout.margin.hasBottom()).isTrue
        assertThat(layout.margin.hasTop()).isTrue
        assertThat(layout.margin.hasLeft()).isTrue
        assertThat(layout.margin.hasRight()).isTrue
    }

    @Test(dependsOnMethods = ["grid layout should be added to layout"])
    fun `grid layout should accept one child component`() {
        val layout = verticalLayout {
            label()
        }
        assertThat(layout.componentCount).isEqualTo(1)
    }

    @Test(dependsOnMethods = ["grid layout should accept one child component"])
    fun `grid layout should accept many child components`() {
        val layout = gridLayout {
            repeat(10) {
                label()
            }
        }
        assertThat(layout.componentCount).isEqualTo(10)
    }
}