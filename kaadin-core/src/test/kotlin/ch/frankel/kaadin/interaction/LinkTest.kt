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
import ch.frankel.kaadin.link
import com.vaadin.server.FontAwesome.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class LinkTest {

    @Test
    fun `link should be added to layout`() {
        val layout = horizontalLayout {
            link("dummy")
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(Link::class.java)
    }

    @Test(dependsOnMethods = arrayOf("link should be added to layout"))
    fun `link should display a specific caption`() {
        val caption = "Hello world"
        val layout = horizontalLayout {
            link(caption)
        }
        val link = layout.getComponent(0) as Link
        assertThat(link.caption).isEqualTo(caption)
    }

    @Test(dependsOnMethods = arrayOf("link should be added to layout"))
    fun `link should open a specific resource`() {
        val target = AMAZON
        val layout = horizontalLayout {
            link("dummy", target)
        }
        val link = layout.getComponent(0) as Link
        assertThat(link.resource).isEqualTo(target)
    }

    @Test(dependsOnMethods = arrayOf("link should be added to layout"))
    fun `link should display a specific caption and open a specific resource`() {
        val caption = "Hello world"
        val target = AMAZON
        val layout = horizontalLayout {
            link(caption, target)
        }
        val link = layout.getComponent(0) as Link
        assertThat(link.caption).isEqualTo(caption)
        assertThat(link.resource).isEqualTo(target)
    }

    @Test(dependsOnMethods = arrayOf("link should be added to layout"))
    fun `link should open in a specific target`() {
        val targetName = "_blank"
        val layout = horizontalLayout {
            link("dummy", targetName = targetName)
        }
        val link = layout.getComponent(0) as Link
        // Not possible to simulate click, must check state
        assertThat(link.targetName).isEqualTo(targetName)
    }

    @Test(dependsOnMethods = arrayOf("link should be added to layout"))
    fun `link should be configurable in the lambda`() {
        val data = "dummy"
        val layout = horizontalLayout {
            link("caption") {
                this.data = data
            }
        }
        val link = layout.getComponent(0) as Link
        assertThat(link.data).isEqualTo(data)
    }
}