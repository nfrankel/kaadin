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
package ch.frankel.kaadin.grid

import ch.frankel.kaadin.*
import com.vaadin.ui.renderers.ButtonRenderer
import com.vaadin.ui.renderers.TextRenderer
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

@Test(dependsOnGroups = ["baseGrid"])
class GridRendererTest {

    @Test
    fun `column should add button renderer`() {
        val layout = horizontalLayout {
            grid(dataSource = GridTestData.container) {
                column("string").buttonRenderer()
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.getStringColumn().renderer)
                .isNotNull
                .isInstanceOf(ButtonRenderer::class.java)
    }

    @Test
    fun `column should add custom renderer`() {
        val layout = horizontalLayout {
            grid(dataSource = GridTestData.container) {
                column("string").renderer(TextRenderer())
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.getStringColumn().renderer)
                .isNotNull
                .isInstanceOf(TextRenderer::class.java)
    }
}