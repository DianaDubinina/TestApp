package searchtickets.app.data.mappers

interface Mapper<E, D> {
    fun mapToDomain(entity: E): D
}
