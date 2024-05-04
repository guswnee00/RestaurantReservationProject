package org.zerobase.restaurantreservationproject.domain.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.zerobase.restaurantreservationproject.domain.restaurant.dto.RestaurantDetail;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;
import org.zerobase.restaurantreservationproject.domain.review.entity.ReviewEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    @Mapping(target = "distance", ignore = true)    // repo 에서 계산 후 set
    @Mapping(target = "averageStarRating", expression = "java(calculateAverageStarRating(entity.getReviews()))")
    @Mapping(target = "reviewCount", expression = "java((long) entity.getReviews().size())")
    @Mapping(target = "reviewComments", expression = "java(getReviewComments(entity.getReviews()))")
    RestaurantDetail fromEntity(RestaurantEntity entity);

    /**
     * 레스토랑 별 평균 별점
     */
    default double calculateAverageStarRating(List<ReviewEntity> reviews) {
        if(reviews.isEmpty()) {
            return 0.0;
        }

        double average = reviews.stream()
                                .mapToDouble(ReviewEntity::getStarRating)
                                .average()
                                .orElse(0.0);

        BigDecimal roundedAverage = BigDecimal.valueOf(average)
                                            .setScale(1, RoundingMode.HALF_UP);

        return roundedAverage.doubleValue();
    }

    default List<String> getReviewComments(List<ReviewEntity> reviews) {
        return reviews.stream()
                .map(ReviewEntity::getComment)
                .collect(Collectors.toList());
    }
}
