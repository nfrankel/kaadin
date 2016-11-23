package ch.frankel.kaadin.size

import ch.frankel.kaadin.*
import com.vaadin.server.*
import com.vaadin.server.Sizeable.Unit.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.*

class HeightTest {

    lateinit var sizeable: Sizeable

    @BeforeMethod
    protected fun setUp() {
        sizeable = Image()
    }

    @Test
    fun `height undefined should set height to -1`() {
        sizeable.heightUndefined()
        assertThat(sizeable.height).isEqualTo(-1f)
    }

    @Test
    fun `height with string values should set height`() {
        sizeable.height("20%")
        assertThat(sizeable.height).isEqualTo(20f)
        assertThat(sizeable.heightUnits).isEqualTo(PERCENTAGE)
    }

    @Test
    fun `height with float and unit should set height`() {
        sizeable.height(30f, PIXELS)
        assertThat(sizeable.height).isEqualTo(30f)
        assertThat(sizeable.heightUnits).isEqualTo(PIXELS)
    }

    @Test
    fun `height as object should set height`() {
        sizeable.height(Height(40f, CM))
        assertThat(sizeable.height).isEqualTo(40f)
        assertThat(sizeable.heightUnits).isEqualTo(CM)
    }
}