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
import com.vaadin.server.Sizeable.Unit.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import java.io.File

class EmbeddedTest {

    @Test
    fun `image should be added to layout`() {
        val layout = horizontalLayout {
            image()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(Image::class.java)
    }

    @Test
    fun `classpath image should be added to layout`() {
        val layout = horizontalLayout {
            classpathImage(source = "/cats-q-c-50-50-9.jpg")
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(Image::class.java)
    }

    @Test
    fun `theme image should be added to layout`() {
        val layout = horizontalLayout {
            themeImage(source = "base/img/tab-arrows.png")
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(Image::class.java)
    }

    @Test
    fun `file image should be added to layout`() {
        val resource = javaClass.getResource("/cats-q-c-50-50-9.jpg")
        val file = File(resource.toURI())
        val layout = horizontalLayout {
            fileImage(source = file.absolutePath)
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(Image::class.java)
    }

    @Test
    fun `image should be configurable`() {
        val width = 20f
        val height = 10f
        val layout = horizontalLayout {
            image {
                width(width, PIXELS)
                height(height, PIXELS)
            }
        }
        val component = layout.getComponent(0) as Image
        assertThat(component).hasFieldOrPropertyWithValue("width", width)
        assertThat(component).hasFieldOrPropertyWithValue("height", height)
    }

    @Test
    fun `flash should be added to layout`() {
        val layout = horizontalLayout {
            flash()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(Flash::class.java)
    }
}