package ch.frankel.kaadin.doc

// tag::importgrid[]
import ch.frankel.kaadin.*
import com.vaadin.data.util.*
import com.vaadin.server.*
import com.vaadin.ui.*

// end::importgrid[]

// tag::simplegridui[]
class SimpleGridUI() : UI() {
    val data = BeanItemContainer<Person>(Person::class.java, Person.all)
    override fun init(request: VaadinRequest) {
        grid(dataSource = data)
    }
}
// end::simplegridui[]

// tag::gridnestedcontainerui[]
class GridNestedContainerUI() : UI() {
    override fun init(request: VaadinRequest) {
        grid {
            beanItemContainer(Person::class.java, Person.all)
        }
    }
}
// end::gridnestedcontainerui[]

// tag::gridwithbeansui[]
class GridWitBeansUI() : UI() {
    override fun init(request: VaadinRequest) {
        grid {
            beanItemContainer(Person::class.java) {
                Person.single //<1>
                Person.all //<2>
            }
        }
    }
}
// end::gridwithbeansui[]
