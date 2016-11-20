package ch.frankel.kaadin.datainput

import ch.frankel.kaadin.*
import com.vaadin.data.util.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class PasswordFieldTest {

    @Test
    fun `password should be added to layout`() {
        val layout = horizontalLayout {
            passwordField()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(PasswordField::class.java)
    }

    @Test(dependsOnMethods = arrayOf("password should be added to layout"))
    fun `password value can be initialized`() {
        val password = "Hello world"
        val layout = horizontalLayout {
            passwordField(value = password)
        }
        val component = layout.getComponent(0) as PasswordField
        assertThat(component).hasFieldOrPropertyWithValue("value", password)
    }

    @Test(dependsOnMethods = arrayOf("password should be added to layout"))
    fun `password value can be initialized via property`() {
        val password = "Hello world"
        val property = ObjectProperty(password)
        val layout = horizontalLayout {
            passwordField(dataSource = property)
        }
        val component = layout.getComponent(0) as PasswordField
        assertThat(component).hasFieldOrPropertyWithValue("value", password)
    }

    @Test(dependsOnMethods = arrayOf("password should be added to layout"))
    fun `password should be configurable`() {
        val password = "Hello world"
        val layout = horizontalLayout {
            passwordField {
                value = password
            }
        }
        val component = layout.getComponent(0) as PasswordField
        assertThat(component).hasFieldOrPropertyWithValue("value", password)
    }
}