package ch.frankel.kaadin.interaction.datapresentation

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
        assertThat(component).isNotNull().isInstanceOf(Audio::class.java)
    }

    @Test(dependsOnMethods = arrayOf("audio should be added to layout"))
    fun `audio can be set caption`() {
        val caption = "dummy caption"
        val layout = horizontalLayout {
            audio(caption)
        }
        val component = layout.getComponent(0) as Audio
        assertThat(component).hasFieldOrPropertyWithValue("caption", caption)
    }

    @Test
    fun `video should be added to layout`() {
        val layout = horizontalLayout {
            video()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(Video::class.java)
    }
}