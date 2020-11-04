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
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class SliderTest {

    @Test
    fun `slider should be added to layout`() {
        val layout = horizontalLayout {
            slider(min = 0, max = 2, resolution = 1)
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull.isInstanceOf(Slider::class.java)
    }

    @Test(dependsOnMethods = ["slider should be added to layout"])
    fun `slider mix and max can be initialized through ints`() {
        val min = 0.0
        val max = 2.0
        val resolution = 1
        val layout = horizontalLayout {
            slider(min = min.toInt(), max = max.toInt(), resolution = resolution)
        }
        val component = layout.getComponent(0) as Slider
        assertThat(component.min).isEqualTo(min)
        assertThat(component.max).isEqualTo(max)
        assertThat(component.resolution).isEqualTo(resolution)
    }

    @Test(dependsOnMethods = ["slider should be added to layout"])
    fun `slider mix and max can be initialized through doubles`() {
        val min = 0.0
        val max = 2.0
        val layout = horizontalLayout {
            slider(min = min, max = max)
        }
        val component = layout.getComponent(0) as Slider
        assertThat(component.min).isEqualTo(min)
        assertThat(component.max).isEqualTo(max)
    }

    @Test(dependsOnMethods = ["slider should be added to layout"])
    fun `slider caption can be initialized`() {
        val caption = "caption"
        val layout = horizontalLayout {
            slider(caption, 0.0, 2.0)
        }
        val component = layout.getComponent(0) as Slider
        assertThat(component.caption).isEqualTo(caption)
    }

    @Test(dependsOnMethods = ["slider should be added to layout"])
    fun `slider caption should be configurable`() {
        val value = 2.0
        val layout = horizontalLayout {
            slider(min = 0.0, max = 2.0) {
                this.value = value
            }
        }
        val component = layout.getComponent(0) as Slider
        assertThat(component.value).isEqualTo(value)
    }
}