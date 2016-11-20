package ch.frankel.kaadin.interaction.datainput

import ch.frankel.kaadin.*
import com.vaadin.data.util.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class TextFieldTest {

    @Test
    fun `text should be added to layout`() {
        val layout = horizontalLayout {
            textField()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(TextField::class.java)
    }

    @Test(dependsOnMethods = arrayOf("text should be added to layout"))
    fun `text value can be initialized`() {
        val text = "Hello world"
        val layout = horizontalLayout {
            textField(value = text)
        }
        val component = layout.getComponent(0) as TextField
        assertThat(component).hasFieldOrPropertyWithValue("value", text)
    }

    @Test(dependsOnMethods = arrayOf("text should be added to layout"))
    fun `text caption can be initialized`() {
        val caption = "Hello world"
        val layout = horizontalLayout {
            textField(caption)
        }
        val component = layout.getComponent(0) as TextField
        assertThat(component).hasFieldOrPropertyWithValue("caption", caption)
    }

    @Test(dependsOnMethods = arrayOf("text should be added to layout"))
    fun `text value can be initialized via property`() {
        val text = "Hello world"
        val property = ObjectProperty(text)
        val layout = horizontalLayout {
            textField(dataSource = property)
        }
        val component = layout.getComponent(0) as TextField
        assertThat(component).hasFieldOrPropertyWithValue("value", text)
    }

    @Test(dependsOnMethods = arrayOf("text should be added to layout"))
    fun `text should be configurable`() {
        val text = "Hello world"
        val layout = horizontalLayout {
            textField {
                value = text
            }
        }
        val component = layout.getComponent(0) as TextField
        assertThat(component).hasFieldOrPropertyWithValue("value", text)
    }
}