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
import com.vaadin.server.FontAwesome.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import java.io.ByteArrayOutputStream

class UploadTest {

    @Test
    fun `upload should be added to layout`() {
        val layout = horizontalLayout {
            upload(uploadReceiver = FakeReceiver())
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(Upload::class.java)
    }

    @Test(dependsOnMethods = arrayOf("upload should be added to layout"))
    fun `upload caption can be initialized`() {
        val caption = "caption"
        val layout = horizontalLayout {
            upload(caption, FakeReceiver())
        }
        val component = layout.getComponent(0) as Upload
        assertThat(component.caption).isEqualTo(caption)
    }

    @Test(dependsOnMethods = arrayOf("upload should be added to layout"))
    fun `upload caption should be configurable`() {
        val layout = horizontalLayout {
            upload(uploadReceiver = FakeReceiver()) {
                icon = ADJUST
            }
        }
        val component = layout.getComponent(0) as Upload
        assertThat(component.icon).isSameAs(ADJUST)
    }
}

private class FakeReceiver : Upload.Receiver {
    override fun receiveUpload(filename: String, mimeType: String) = ByteArrayOutputStream()
}