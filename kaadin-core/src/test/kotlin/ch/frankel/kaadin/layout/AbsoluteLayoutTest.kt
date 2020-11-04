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

class AbsoluteLayoutTest {

    @Test
    fun `absolute layout should be added to layout`() {
        val layout = horizontalLayout {
            absoluteLayout()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull.isInstanceOf(AbsoluteLayout::class.java)
    }

    @Test(dependsOnMethods = ["absolute layout should be added to layout"])
    fun `absolute layout should accept one child component`() {
        val layout = absoluteLayout {
            at("left: 50px; top: 50px;") put Label("a label")
        }
        assertThat(layout.componentCount).isEqualTo(1)
    }

    @Test(dependsOnMethods = ["absolute layout should accept one child component"])
    fun `absolute layout should accept many child components`() {
        val layout = absoluteLayout {
                at("left: 50px; top: 50px;") put Label("a label")
                at("right: 10px; top: 50px;") put Label("another label")
                at("left: 10px; bottom: 10px;") put TextField("caption", "a value")
        }
        assertThat(layout.componentCount).isEqualTo(3)
    }
}