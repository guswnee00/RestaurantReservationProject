package org.zerobase.restaurantreservationproject.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zerobase.restaurantreservationproject.domain.review.entity.ReviewEntity;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    @Query(value = " SELECT COUNT(*) > 0 " +
            " FROM REVIEW r " +
            " JOIN USER u ON r.user.id = u.id " +
            " JOIN RESTAURANT res ON r.restaurant.id = res.id " +
            " WHERE u.username = :username " +
            " AND res.restaurantName = :restaurantName ",
            nativeQuery = true)
    boolean writeReviewAlready(String username, String restaurantName);

    boolean existsByUser_Username(String username);

    ReviewEntity findByUser_Username(String username);

    ReviewEntity findByUser_UsernameAndRestaurant_RestaurantName(String username, String restaurantName);

    ReviewEntity findByRestaurant_Manager_UsernameAndId(String managerName, Long id);

}
