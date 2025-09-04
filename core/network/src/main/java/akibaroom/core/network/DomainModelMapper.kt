package akibaroom.core.network

interface DomainModelMapper<Entity, Model> {
    fun toModel(entity: Entity): Model
}
