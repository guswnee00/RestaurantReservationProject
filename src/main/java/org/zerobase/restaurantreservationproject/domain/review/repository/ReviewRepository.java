package org.zerobase.restaurantreservationproject.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerobase.restaurantreservationproject.domain.review.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
