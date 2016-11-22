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

class SplitPanelTest {

    @Test
    fun `split panel should be added to layout`() {
        val layout = horizontalLayout {
            verticalSplitPanel()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(AbstractSplitPanel::class.java)
    }

    @Test(dependsOnMethods = arrayOf("split panel should be added to layout"))
    fun `split panel should accept one child component`() {
        val layout = horizontalLayout {
            horizontalSplitPanel {
                label()
            }
        }
        val component = layout.getComponent(0) as AbstractSplitPanel
        assertThat(component.componentCount).isEqualTo(1)
        assertThat(component.iterator().next()).isExactlyInstanceOf(Label::class.java)
    }

    @Test(dependsOnMethods = arrayOf("split panel should be added to layout"))
    fun `split panel should accept 2 child components`() {
        val layout = horizontalLayout {
            horizontalSplitPanel {
                label()
                textField()
            }
        }
        val component = layout.getComponent(0) as AbstractSplitPanel
        assertThat(component.componentCount).isEqualTo(2)
        val components = component.iterator()
        assertThat(components.next()).isExactlyInstanceOf(Label::class.java)
        assertThat(components.next()).isExactlyInstanceOf(TextField::class.java)
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun `panel should not accept more than 2 child components`() {
        horizontalLayout {
            horizontalSplitPanel {
                label()
                textField()
                label()
            }
        }
    }
}