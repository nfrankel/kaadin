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
package ch.frankel.kaadin.datapresentation

import ch.frankel.kaadin.*
import com.vaadin.server.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import java.io.File

class FrameTest {

    @Test
    fun `frame should be added to layout`() {
        val layout = horizontalLayout {
            frame()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(BrowserFrame::class.java)
    }

    @Test
    fun `classpath frame should be added to layout`() {
        val layout = horizontalLayout {
            classpathFrame(source = "/cats-q-c-50-50-9.jpg")
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(BrowserFrame::class.java)
    }

    @Test
    fun `theme frame should be added to layout`() {
        val layout = horizontalLayout {
            themeFrame(source = "base/img/tab-arrows.png")
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(BrowserFrame::class.java)
    }

    @Test
    fun `file frame should be added to layout`() {
        val resource = javaClass.getResource("/cats-q-c-50-50-9.jpg")
        val file = File(resource.toURI())
        val layout = horizontalLayout {
            fileFrame(source = file.absolutePath)
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(BrowserFrame::class.java)
    }

    @Test
    fun `frame should be configurable`() {
        val width = 20f
        val height = 10f
        val layout = horizontalLayout {
            frame {
                width(width, Sizeable.Unit.PIXELS)
                height(height, Sizeable.Unit.PIXELS)
            }
        }
        val component = layout.getComponent(0) as BrowserFrame
        assertThat(component).hasFieldOrPropertyWithValue("width", width)
        assertThat(component).hasFieldOrPropertyWithValue("height", height)
    }
}