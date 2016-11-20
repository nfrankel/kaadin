package ch.frankel.kaadin.interaction.datainput

import ch.frankel.kaadin.*
import com.vaadin.data.util.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class TextAreaTest {

    @Test
    fun `text area should be added to layout`() {
        val layout = horizontalLayout {
            textArea()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(TextArea::class.java)
    }

    @Test(dependsOnMethods = arrayOf("text area should be added to layout"))
    fun `text area value can be initialized`() {
        val text = "Hello world"
        val layout = horizontalLayout {
            textArea(value = text)
        }
        val component = layout.getComponent(0) as TextArea
        assertThat(component).hasFieldOrPropertyWithValue("value", text)
    }

    @Test(dependsOnMethods = arrayOf("text area should be added to layout"))
    fun `text area value can be initialized via property`() {
        val text = "Hello world"
        val property = ObjectProperty(text)
        val layout = horizontalLayout {
            textArea(dataSource = property)
        }
        val component = layout.getComponent(0) as TextArea
        assertThat(component).hasFieldOrPropertyWithValue("value", text)
    }

    @Test(dependsOnMethods = arrayOf("text area should be added to layout"))
    fun `text area should be configurable`() {
        val text = "Hello world"
        val layout = horizontalLayout {
            textArea {
                value = text
            }
        }
        val component = layout.getComponent(0) as TextArea
        assertThat(component).hasFieldOrPropertyWithValue("value", text)
    }
}