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

class PopupViewTest {

    @Test
    fun `popup should be added to layout via nested API`() {
        val layout = horizontalLayout {
            popupView("dummy") {
                label()
            }
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull.isInstanceOf(PopupView::class.java)
    }

    @Test(dependsOnMethods = ["popup should be added to layout via nested API"])
    fun `popup should display component when clicked via nested API`() {
        val layout = horizontalLayout {
            popupView("dummy") {
                label()
            }
        }
        val component = layout.getComponent(0) as PopupView
        val popupComponent = component.content?.popupComponent
        assertThat(popupComponent).isNotNull.isInstanceOf(Label::class.java)
    }

    @Test
    fun `popup should be added to layout via parameter`() {
        val popup = object : PopupView.Content {
            override fun getPopupComponent() = Label()
            override fun getMinimizedValueAsHTML() = "dummy"
        }
        val layout = horizontalLayout {
            popupView(popup)
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull.isInstanceOf(PopupView::class.java)
    }

    @Test(dependsOnMethods = ["popup should be added to layout via nested API"])
    fun `popup should be configurable`() {
        val layout = horizontalLayout {
            popupView("dummy") {
                label()
                isPopupVisible = true
            }
        }
        val component = layout.getComponent(0) as PopupView
        assertThat(component.isPopupVisible).isTrue
    }
}