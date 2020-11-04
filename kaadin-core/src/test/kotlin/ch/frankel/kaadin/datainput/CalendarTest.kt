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
import com.vaadin.ui.Calendar
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import java.util.*

class CalendarTest {

    @Test
    fun `calendar should be added to layout`() {
        val layout = horizontalLayout {
            calendar()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull.isInstanceOf(Calendar::class.java)
    }

    @Test(dependsOnMethods = ["calendar should be added to layout"])
    fun `calendar caption can be initialized`() {
        val caption = "dummy caption"
        val layout = horizontalLayout {
            calendar(caption)
        }
        val component = layout.getComponent(0) as Calendar
        assertThat(component.caption).isEqualTo(caption)
    }

    @Test(dependsOnMethods = ["calendar should be added to layout"])
    fun `calendar should be configurable`() {
        val date = Date()
        val layout = horizontalLayout {
            calendar {
                startDate = date
            }
        }
        val component = layout.getComponent(0) as Calendar
        assertThat(component.startDate).isEqualTo(date)
    }
}