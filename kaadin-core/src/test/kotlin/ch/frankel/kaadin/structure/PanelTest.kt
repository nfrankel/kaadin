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
package ch.frankel.kaadin.structure

import ch.frankel.kaadin.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class PanelTest {

    @Test
    fun `panel should be added to layout`() {
        val layout = horizontalLayout {
            panel()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull.isInstanceOf(Panel::class.java)
    }

    @Test(dependsOnMethods = ["panel should be added to layout"])
    fun `panel caption can be initialized`() {
        val caption = "caption"
        val layout = horizontalLayout {
            panel(caption)
        }
        val component = layout.getComponent(0) as Panel
        assertThat(component.caption).isEqualTo(caption)
    }

    @Test(dependsOnMethods = ["panel should be added to layout"])
    fun `panel should accept one child component`() {
        val layout = horizontalLayout {
            panel {
                label()
            }
        }
        val component = layout.getComponent(0) as Panel
        assertThat(component.componentCount).isEqualTo(1)
    }

    @Test(expectedExceptions = [IllegalArgumentException::class])
    fun `panel should not accept more than one child component`() {
        horizontalLayout {
            panel {
                label()
                label()
            }
        }
    }
}