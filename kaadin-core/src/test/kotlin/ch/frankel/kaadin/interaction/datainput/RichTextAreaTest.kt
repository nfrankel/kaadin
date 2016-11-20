package ch.frankel.kaadin.interaction.datainput

import ch.frankel.kaadin.*
import com.vaadin.data.util.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class RichTextAreaTest {

    @Test
    fun `rich text area should be added to layout`() {
        val layout = horizontalLayout {
            richTextArea()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(RichTextArea::class.java)
    }

    @Test(dependsOnMethods = arrayOf("rich text area should be added to layout"))
    fun `rich text area value can be initialized`() {
        val text = "Hello world"
        val layout = horizontalLayout {
            richTextArea(value = text)
        }
        val component = layout.getComponent(0) as RichTextArea
        assertThat(component).hasFieldOrPropertyWithValue("value", text)
    }

    @Test(dependsOnMethods = arrayOf("rich text area should be added to layout"))
    fun `rich text area value can be initialized via property`() {
        val text = "Hello world"
        val property = ObjectProperty(text)
        val layout = horizontalLayout {
            richTextArea(dataSource = property)
        }
        val component = layout.getComponent(0) as RichTextArea
        assertThat(component).hasFieldOrPropertyWithValue("value", text)
    }

    @Test(dependsOnMethods = arrayOf("rich text area should be added to layout"))
    fun `rich text area should be configurable`() {
        val text = "Hello world"
        val layout = horizontalLayout {
            richTextArea {
                value = text
            }
        }
        val component = layout.getComponent(0) as RichTextArea
        assertThat(component).hasFieldOrPropertyWithValue("value", text)
    }
}