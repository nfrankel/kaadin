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
import com.vaadin.server.*
import org.mockito.Mockito.*
import org.testng.annotations.*
import java.util.Locale.*

class SessionTest {

    private lateinit var vaadinSession: VaadinSession

    @BeforeMethod
    private fun setUp() {
        vaadinSession = mock(VaadinSession::class.java)
        VaadinSession.setCurrent(vaadinSession)
    }

    @Test
    fun `session should be configurable`() {
        session {
            this.locale = FRENCH
        }
        verify(vaadinSession).locale = FRENCH
    }

    @Test
    fun `session locale should be set`() {
        sessionLocale(FRENCH)
        verify(vaadinSession).locale = FRENCH
    }

    @AfterMethod
    private fun tearDown() {
        VaadinSession.setCurrent(null)
    }
}