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

import ch.frankel.kaadin.horizontalLayout
import ch.frankel.kaadin.nativeButton
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class NativeButtonTest {

    @Test
    fun `native button should be added to layout`() {
        val layout = horizontalLayout {
            nativeButton()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(NativeButton::class.java)
    }

    @Test(dependsOnMethods = arrayOf("native button should be added to layout"))
    fun `native button should display a specific caption`() {
        val caption = "Hello world"
        val layout = horizontalLayout {
            nativeButton(caption)
        }
        val nativeButton = layout.getComponent(0) as NativeButton
        assertThat(nativeButton.caption).isEqualTo(caption)
    }

    @Test(dependsOnMethods = arrayOf("native button should be added to layout"))
    fun `native button should react on a specific click listener`() {
        var clicked = false
        val layout = horizontalLayout {
            nativeButton(clickListener = { clicked = true })
        }
        val nativeButton = layout.getComponent(0) as NativeButton
        nativeButton.click()
        assertThat(clicked).isTrue()
    }

    @Test(dependsOnMethods = arrayOf("native button should be added to layout"))
    fun `native button should be configurable in the lambda`() {
        val data = "dummy"
        val caption = "Hello world"
        val layout = horizontalLayout {
            nativeButton() {
                this.caption = caption
                this.data = data
            }
        }
        val nativeButton = layout.getComponent(0) as NativeButton
        assertThat(nativeButton.data).isEqualTo(data)
        assertThat(nativeButton.caption).isEqualTo(caption)
    }
}