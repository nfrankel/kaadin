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
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.*
import java.net.URI

class PageTest {

    @BeforeMethod
    private fun setUp() {
        initializeAndSetCurrentUI()
    }

    @Test
    fun `page should be configurable`() {
        val fragment = "a fragment"
        page {
            uriFragment = fragment
        }
        assertThat(Page.getCurrent().uriFragment).isEqualTo(fragment)
    }

    @Test
    fun `page location can be set with string`() {
        val location = "http://localhost:8080"
        location(location)
        assertThat(Page.getCurrent().location).isEqualTo(URI(location))
    }

    @Test
    fun `page location can be set with URI`() {
        val location = URI("http://localhost:8080")
        location(location)
        assertThat(Page.getCurrent().location).isEqualTo(location)
    }

    @Test
    fun `page uri fragment can be set`() {
        val uriFragment = "a fragment"
        uriFragment(uriFragment)
        assertThat(Page.getCurrent().uriFragment).isEqualTo(uriFragment)
    }
}