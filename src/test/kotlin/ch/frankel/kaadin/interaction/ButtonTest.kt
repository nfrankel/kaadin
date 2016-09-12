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

package ch.frankel.kaadin.interaction

import ch.frankel.kaadin.button
import ch.frankel.kaadin.hLayout
import com.vaadin.server.FontAwesome.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class ButtonTest {

    @Test
    fun `button should be added to layout`() {
        val layout = hLayout {
            button()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(Button::class.java)
    }

    @Test(dependsOnMethods = arrayOf("button should be added to layout"))
    fun `button should display a specific caption`() {
        val caption = "Hello world"
        val layout = hLayout {
            button(caption)
        }
        val button = layout.getComponent(0) as Button
        assertThat(button.caption).isEqualTo(caption)
    }

    @Test(dependsOnMethods = arrayOf("button should be added to layout"))
    fun `button should display a specific icon`() {
        val icon = AMAZON
        val layout = hLayout {
            button(icon = icon)
        }
        val button = layout.getComponent(0) as Button
        assertThat(button.icon).isSameAs(icon)
    }

    @Test(dependsOnMethods = arrayOf("button should be added to layout"))
    fun `button should display a specific caption and icon`() {
        val caption = "Hello world"
        val icon = AMAZON
        val layout = hLayout {
            button(caption, icon)
        }
        val button = layout.getComponent(0) as Button
        assertThat(button.caption).isEqualTo(caption)
        assertThat(button.icon).isSameAs(icon)
    }

    @Test(dependsOnMethods = arrayOf("button should be added to layout"))
    fun `button should react on a specific click listener`() {
        var clicked = false
        val layout = hLayout {
            button(clickListener = { clicked = true })
        }
        val button = layout.getComponent(0) as Button
        button.click()
        assertThat(clicked).isTrue()
    }

    @Test(dependsOnMethods = arrayOf("button should be added to layout"))
    fun `button should be configurable in the lambda`() {
        val data = "dummy"
        val caption = "Hello world"
        val layout = hLayout {
            button() {
                this.caption = caption
                this.data = data
            }
        }
        val button = layout.getComponent(0) as Button
        assertThat(button.data).isEqualTo(data)
        assertThat(button.caption).isEqualTo(caption)
    }
}