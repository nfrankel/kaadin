package ch.frankel.kaadin.datainput

import ch.frankel.kaadin.*
import com.vaadin.ui.Calendar
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import java.util.*

class CalendarTest {

    @Test
    fun `calendar should be added to layout`() {
        val layout = horizontalLayout {
            calendar()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(Calendar::class.java)
    }

    @Test(dependsOnMethods = arrayOf("calendar should be added to layout"))
    fun `calendar caption can be initialized`() {
        val caption = "dummy caption"
        val layout = horizontalLayout {
            calendar(caption)
        }
        val component = layout.getComponent(0) as Calendar
        assertThat(component).hasFieldOrPropertyWithValue("caption", caption)
    }

    @Test(dependsOnMethods = arrayOf("calendar should be added to layout"))
    fun `calendar should be configurable`() {
        val date = Date()
        val layout = horizontalLayout {
            calendar {
                startDate = date
            }
        }
        val component = layout.getComponent(0) as Calendar
        assertThat(component).hasFieldOrPropertyWithValue("startDate", date)
    }
}