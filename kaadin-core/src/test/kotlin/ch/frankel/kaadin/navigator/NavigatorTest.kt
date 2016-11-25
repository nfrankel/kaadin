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
package ch.frankel.kaadin.navigator

import ch.frankel.kaadin.*
import com.vaadin.navigator.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.*

internal const val NAVIGATOR_GROUP = "navigator"

@Test(groups = arrayOf(NAVIGATOR_GROUP))
class NavigatorTest {

    @BeforeMethod
    fun setUp() {
        initializeAndSetCurrentUI()
    }

    @Test
    fun `navigator can be initialized with a container`() {
        ui {
            navigator(verticalLayout {
                label()
                textField("caption", "value")
            })
        }
        val viewName = "View"
        val ui = ui()
        ui.navigator.addView(viewName, HorizontalLayoutView())
        ui.navigator.navigateTo(viewName)
        val container = ui.iterator().next()
        assertThat(container).isInstanceOf(VerticalLayout::class.java)
        val layout = container as VerticalLayout
        assertThat(layout.componentCount).isNotEqualTo(2).isEqualTo(1)
        val component = layout.iterator().next()
        assertThat(component).isInstanceOf(HorizontalLayoutView::class.java)
    }

    @Test
    fun `navigator can be initialized with a single component container`() {
        ui {
            navigator(panel {
                label()
            })
        }
        val viewName = "View"
        val ui = ui()
        ui.navigator.addView(viewName, HorizontalLayoutView())
        ui.navigator.navigateTo(viewName)
        val container = ui.iterator().next()
        assertThat(container).isInstanceOf(Panel::class.java)
        val layout = container as Panel
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.iterator().next()
        assertThat(component).isInstanceOf(HorizontalLayoutView::class.java)
    }

    @Test
    fun `navigator can be initialized with a view display`() {
        var displayedView: View? = null
        ui {
            navigator({ displayedView = it} )
        }
        val viewName = "View"
        val ui = ui()
        ui.navigator.addView(viewName, HorizontalLayoutView())
        ui.navigator.navigateTo(viewName)
        assertThat(displayedView!!).isInstanceOf(HorizontalLayoutView::class.java)
    }

    @Test(dependsOnMethods = arrayOf("navigator can be initialized with a view display"))
    fun `navigator can be configurable`() {
        var displayedView: View? = null
        ui {
            navigator({ displayedView = it} ) {
                val viewName = "dummy"
                addView(viewName, HorizontalLayoutView())
                navigateTo(viewName)
            }
        }
        assertThat(displayedView!!).isInstanceOf(HorizontalLayoutView::class.java)
    }
}