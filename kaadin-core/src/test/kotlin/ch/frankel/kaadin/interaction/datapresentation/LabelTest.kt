package ch.frankel.kaadin.interaction.datapresentation

import ch.frankel.kaadin.*
import com.vaadin.data.util.*
import com.vaadin.shared.ui.label.ContentMode.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class LabelTest {

    @Test
    fun `label should be added to layout`() {
        val layout = horizontalLayout {
            label()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(Label::class.java)
    }

    @Test(dependsOnMethods = arrayOf("label should be added to layout"))
    fun `label can be initialized with text`() {
        val text = "Hello world"
        val layout = horizontalLayout {
            label(text)
        }
        val component = layout.getComponent(0) as Label
        assertThat(component).hasFieldOrPropertyWithValue("value", text)
    }

    @Test
    fun `html label should be added to layout`() {
        val layout = horizontalLayout {
            html()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(Label::class.java)
    }

    @Test(dependsOnMethods = arrayOf("html label should be added to layout"))
    fun `html label should be added be of type html`() {
        val layout = horizontalLayout {
            html()
        }
        val component = layout.getComponent(0) as Label
        assertThat(component).hasFieldOrPropertyWithValue("contentMode", HTML)
    }

    @Test(dependsOnMethods = arrayOf("html label should be added to layout"))
    fun `html label should be initialized with property`() {
        val text = "Hello world"
        val property = ObjectProperty(text)
        val layout = horizontalLayout {
            html(property)
        }
        val component = layout.getComponent(0) as Label
        assertThat(component).hasFieldOrPropertyWithValue("value", text)
    }

    @Test(dependsOnMethods = arrayOf("html label should be added to layout"))
    fun `html label should be configurable`() {
        val text = "Hello world"
        val layout = horizontalLayout {
            html {
                value = text
            }
        }
        val component = layout.getComponent(0) as Label
        assertThat(component).hasFieldOrPropertyWithValue("value", text)
    }
}