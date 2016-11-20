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
import ch.frankel.kaadin.menuBar
import ch.frankel.kaadin.menuItem
import ch.frankel.kaadin.select
import com.vaadin.server.FontAwesome.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class MenuBarTest {

    @Test
    fun `menu bar should be added to layout`() {
        val layout = horizontalLayout {
            menuBar()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(MenuBar::class.java)
    }

    @Test(dependsOnMethods = arrayOf("menu bar should be added to layout"))
    fun `menu bar should be configurable`() {
        val icon = AMAZON
        val layout = horizontalLayout {
            menuBar() {
                this.icon = icon
            }
        }
        val menuBar = layout.getComponent(0) as MenuBar
        assertThat(menuBar.icon).isNotNull().isSameAs(icon)
    }

    @Test(dependsOnMethods = arrayOf("menu bar should be added to layout"))
    fun `menu items should be added to menu bar`() {
        val size = 3
        val layout = horizontalLayout {
            menuBar() {
                IntRange(0, size).forEach {
                    menuItem("Hello World", onClick = { })
                }
            }
        }
        val menuBar = layout.getComponent(0) as MenuBar
        assertThat(menuBar.items).isNotNull().isNotEmpty().hasSize(size + 1)
    }

    @Test(dependsOnMethods = arrayOf("menu items should be added to menu bar"))
    fun `menu items should be configurable`() {
        val icon = AMAZON
        val layout = horizontalLayout {
            menuBar() {
                menuItem("Hello World", onClick = { }) {
                    this.icon = icon
                }
            }
        }
        val menuBar = layout.getComponent(0) as MenuBar
        assertThat(menuBar.items[0].icon).isNotNull().isSameAs(icon)
    }

    @Test(dependsOnMethods = arrayOf("menu items should be added to menu bar"))
    fun `menu items should display text and react on click`() {
        val size = 3
        val caption = "Hello World"
        val clicked = mutableListOf(false, false, false, false)
        val range = IntRange(0, size)
        val layout = horizontalLayout {
            menuBar() {
                range.forEach { i ->
                    menuItem("$caption $i", onClick = { clicked[i] = true })
                }
            }
        }
        val menuBar = layout.getComponent(0) as MenuBar
        range.forEach {
            val item = menuBar.items[it]
            assertThat(item.text).isEqualTo("$caption $it")
            item.select()
            assertThat(clicked[it]).isTrue()
        }
    }
}