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
package ch.frankel.kaadin.common

import ch.frankel.kaadin.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.*
import java.util.Locale.*

class UITest {

    @BeforeMethod
    private fun setUp() {
        initializeAndSetCurrentUI()
    }

    @Test
    fun `ui should be configurable`() {
        ui {
            pollInterval = 5
        }
        assertThat(UI.getCurrent().pollInterval).isEqualTo(5)
    }

    @Test
    fun `ui theme can be set`() {
        val theme = "valo"
        theme(theme)
        assertThat(UI.getCurrent().theme).isEqualTo(theme)
    }

    @Test
    fun `ui locale can be set`() {
        uiLocale(FRENCH)
        assertThat(UI.getCurrent().locale).isEqualTo(FRENCH)
    }
}