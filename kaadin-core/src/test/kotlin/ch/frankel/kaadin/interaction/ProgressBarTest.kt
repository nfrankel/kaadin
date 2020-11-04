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
import ch.frankel.kaadin.progressBar
import com.vaadin.data.util.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class ProgressBarTest {

    @Test
    fun `progress bar should be added to layout`() {
        val layout = horizontalLayout {
            progressBar()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull.isInstanceOf(ProgressBar::class.java)
    }

    @Test(dependsOnMethods = ["progress bar should be added to layout"])
    fun `progress bar should display a progress`() {
        val value = 0.75f
        val layout = horizontalLayout {
            progressBar(0.75f)
        }
        val progressBar = layout.getComponent(0) as ProgressBar
        assertThat(progressBar.value).isEqualTo(value)
    }

    @Test(dependsOnMethods = ["progress bar should be added to layout"])
    fun `progress bar should display a progress based on a property`() {
        val property = ObjectProperty(0f)
        val layout = horizontalLayout {
            progressBar(dataSource = property)
        }
        property.value = 0.75f
        val progressBar = layout.getComponent(0) as ProgressBar
        assertThat(progressBar.value).isEqualTo(property.value)
    }
}