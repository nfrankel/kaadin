package ch.frankel.kaadin.size

import ch.frankel.kaadin.*
import com.vaadin.server.*
import com.vaadin.server.Sizeable.Unit.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.*

class SizeTest {

    lateinit var sizeable: Sizeable

    @BeforeMethod
    protected fun setUp() {
        sizeable = Image()
    }

    @Test
    fun `size full should set width and height to 100`() {
        sizeable.sizeFull()
        assertThat(sizeable.width).isEqualTo(100f)
        assertThat(sizeable.height).isEqualTo(100f)
        assertThat(sizeable.widthUnits).isEqualTo(PERCENTAGE)
        assertThat(sizeable.heightUnits).isEqualTo(PERCENTAGE)
    }

    @Test
    fun `size undefined should set width and height to -1`() {
        sizeable.sizeUndefined()
        assertThat(sizeable.width).isEqualTo(-1f)
        assertThat(sizeable.height).isEqualTo(-1f)
    }

    @Test
    fun `size with objects should set width and height`() {
        sizeable.size(Width(20f, PERCENTAGE), Height(30f, PERCENTAGE))
        assertThat(sizeable.width).isEqualTo(20f)
        assertThat(sizeable.height).isEqualTo(30f)
        assertThat(sizeable.widthUnits).isEqualTo(PERCENTAGE)
        assertThat(sizeable.heightUnits).isEqualTo(PERCENTAGE)
    }

    @Test
    fun `size with string values should set width and height`() {
        sizeable.size("20%", "30%")
        assertThat(sizeable.width).isEqualTo(20f)
        assertThat(sizeable.height).isEqualTo(30f)
        assertThat(sizeable.widthUnits).isEqualTo(PERCENTAGE)
        assertThat(sizeable.heightUnits).isEqualTo(PERCENTAGE)
    }

    @Test
    fun `size with float and unit should set width and height`() {
        sizeable.size(20f, 30f, PIXELS)
        assertThat(sizeable.width).isEqualTo(20f)
        assertThat(sizeable.height).isEqualTo(30f)
        assertThat(sizeable.widthUnits).isEqualTo(PIXELS)
        assertThat(sizeable.heightUnits).isEqualTo(PIXELS)
    }

    @Test
    fun `size with float and 2 units should set width and height`() {
        sizeable.size(20f, CM, 30f, EM)
        assertThat(sizeable.width).isEqualTo(20f)
        assertThat(sizeable.height).isEqualTo(30f)
        assertThat(sizeable.widthUnits).isEqualTo(CM)
        assertThat(sizeable.heightUnits).isEqualTo(EM)
    }
}