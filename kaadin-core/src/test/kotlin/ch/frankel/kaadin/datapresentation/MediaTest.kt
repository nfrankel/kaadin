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
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class MediaTest {

    @Test
    fun `audio should be added to layout`() {
        val layout = horizontalLayout {
            audio()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull.isInstanceOf(Audio::class.java)
    }

    @Test(dependsOnMethods = ["audio should be added to layout"])
    fun `audio can be set caption`() {
        val caption = "dummy caption"
        val layout = horizontalLayout {
            audio(caption)
        }
        val component = layout.getComponent(0) as Audio
        assertThat(component.caption).isEqualTo(caption)
    }

    @Test
    fun `video should be added to layout`() {
        val layout = horizontalLayout {
            video()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull.isInstanceOf(Video::class.java)
    }
}