package ch.frankel.kaadin.datainput

import ch.frankel.kaadin.*
import com.vaadin.data.util.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import java.util.*

class DateFieldTest {

    @Test
    fun `date field should be added to layout`() {
        val layout = horizontalLayout {
            dateField()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(DateField::class.java)
    }

    @Test(dependsOnMethods = arrayOf("date field should be added to layout"))
    fun `date field value can be initialized`() {
        val date = Date()
        val layout = horizontalLayout {
            dateField(value = date)
        }
        val component = layout.getComponent(0) as DateField
        assertThat(component).hasFieldOrPropertyWithValue("value", date)
    }

    @Test(dependsOnMethods = arrayOf("date field should be added to layout"))
    fun `date field value can be initialized via property`() {
        val date = Date()
        val property = ObjectProperty(date)
        val layout = horizontalLayout {
            dateField(dataSource = property)
        }
        val component = layout.getComponent(0) as DateField
        assertThat(component).hasFieldOrPropertyWithValue("value", date)
    }

    @Test(dependsOnMethods = arrayOf("date field should be added to layout"))
    fun `date field should be configurable`() {
        val date = Date()
        val layout = horizontalLayout {
            dateField {
                value = date
            }
        }
        val component = layout.getComponent(0) as DateField
        assertThat(component).hasFieldOrPropertyWithValue("value", date)
    }
}