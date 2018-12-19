package com.exercise.registration.mapper;

/**
 * Convert database entity object to DTO and opposite
 * @param <E> entity object
 * @param <D> DTO object
 */
public interface Mapper<E, D> {

    /**
     * Convert DTO to entity
     * @param dto dto to convert
     * @return entity
     */
    E convertToEntity(D dto);

    /**
     * Convert entity to DTO
     * @param entity to convert
     * @return DTO
     */
    D convertToDTO(E entity);
}
