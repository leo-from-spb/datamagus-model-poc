@file:Suppress("conflicting_inherited_jvm_declarations")

package lb.dm.meta

import lb.util.ext.pluralize

import kotlin.reflect.KClass
import kotlin.reflect.KProperty

sealed class MetaMatter (name: String)
{
    @JvmField val name:  String = name.decapitalize()
    @JvmField val names: String = name.decapitalize().pluralize()
    @JvmField val Name:  String = name.capitalize()
    @JvmField val Names: String = name.capitalize().pluralize()

}


class MetaEntity : MetaMatter
{
    val clazz: KClass<Any>

    val properties: MutableList<MetaProperty> = kotlin.collections.ArrayList()

    constructor(klass: KClass<Any>)
        : super(klass.simpleName!!)
    {
        this.clazz = klass
    }
}



class MetaFamily : MetaMatter
{
    val entity: MetaEntity

    constructor(entity: MetaEntity)
        : super(entity.name)
    {
        this.entity = entity
    }
}



class MetaProperty : MetaMatter
{
    val property: KProperty<Any>

    constructor(property: KProperty<Any>)
        : super(property.name)
    {
        this.property = property
    }
}



