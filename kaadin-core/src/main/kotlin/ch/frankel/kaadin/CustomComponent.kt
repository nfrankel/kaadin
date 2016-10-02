package ch.frankel.kaadin

import com.vaadin.ui.*

class CustomComponentWrapper() : CustomComponent() {
    override public fun setCompositionRoot(compositionRoot: Component) {
        super.setCompositionRoot(compositionRoot)
    }
}

fun <T: CustomComponent> HasComponents.customComponent(customComponent: T, init: CustomComponent.() -> Unit = {}) = customComponent
        .apply(init)
        .addTo(this)
fun <T: Component> HasComponents.customComponent(component: T, init: CustomComponent.() -> Unit = {}) = CustomComponent(component)
        .apply(init)
        .addTo(this)
fun HasComponents.customComponent(init: CustomComponentWrapper.() -> Unit = {}) = CustomComponentWrapper()
        .apply(init)
        .addTo(this)