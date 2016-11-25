package ch.frankel.kaadin.size

import ch.frankel.kaadin.*
import com.vaadin.server.*
import com.vaadin.server.Sizeable.Unit.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.*

class WidthTest {

    lateinit var sizeable: Sizeable

    @BeforeMethod
    protected fun setUp() {
        sizeable = Image()
    }

    @Test
    fun `width undefined should set width to -1`() {
        sizeable.widthUndefined()
        assertThat(sizeable.width).isEqualTo(-1f)
    }

    @Test
    fun `width with string values should set width`() {
        sizeable.width("20%")
        assertThat(sizeable.width).isEqualTo(20f)
        assertThat(sizeable.widthUnits).isEqualTo(PERCENTAGE)
    }

    @Test
    fun `width with float and unit should set width`() {
        sizeable.width(30f, PIXELS)
        assertThat(sizeable.width).isEqualTo(30f)
        assertThat(sizeable.widthUnits).isEqualTo(PIXELS)
    }

    @Test
    fun `width as object should set width`() {
        sizeable.width(Width(40f, CM))
        assertThat(sizeable.width).isEqualTo(40f)
        assertThat(sizeable.widthUnits).isEqualTo(CM)
    }
}