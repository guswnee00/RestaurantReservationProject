package org.zerobase.restaurantreservationproject.domain.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.zerobase.restaurantreservationproject.domain.restaurant.dto.RestaurantDto;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    RestaurantDto toDto(RestaurantEntity entity);
    RestaurantEntity toEntity(RestaurantDto dto);

    void updateEntityFromDto(RestaurantDto dto, @MappingTarget RestaurantEntity entity);

}
