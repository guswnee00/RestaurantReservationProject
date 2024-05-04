package org.zerobase.restaurantreservationproject.domain.restaurant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    boolean existsByManager_Username(String managerName);
    RestaurantEntity findByRestaurantName(String restaurantName);
    RestaurantEntity findByManager_Username(String managerName);

    // 내 위치와 레스토랑의 거리
    @Query(value = " SELECT (6371 * acos(cos(radians(:lat)) * cos(radians(r.lat)) " +
            " * cos(radians(r.lnt) - radians(:lnt)) + sin(radians(:lat)) " +
            " * sin(radians(r.lat)))) AS distance " +
            " FROM Restaurant r " +
            " WHERE r.restaurantName = :restaurantName ",
            nativeQuery = true)
    Double findRestaurantByDistance(double lat, double lnt, String restaurantName);

    // 내 위치와 가까운 거리순
    @Query(value = "SELECT *, " +
            "(6371 * acos(cos(radians(:lat)) * cos(radians(lat)) " +
            "* cos(radians(lnt) - radians(:lnt)) + sin(radians(:lat)) " +
            "* sin(radians(lat)))) AS distance " +
            "FROM Restaurant " +
            "ORDER BY distance ASC",
            countQuery = "SELECT count(*) FROM Restaurant",
            nativeQuery = true)
    Page<RestaurantEntity> findRestaurantsNearby(double lat, double lnt, Pageable pageable);

    // 평균 별점 높은 순
    @Query("SELECT r FROM RESTAURANT r LEFT JOIN r.reviews rev GROUP BY r.id ORDER BY AVG(rev.starRating) DESC")
    Page<RestaurantEntity> findRestaurantsOrderByAverageStarRating(Pageable pageable);

    // 레스토랑 이름 가나다순
    Page<RestaurantEntity> findAllByOrderByRestaurantNameAsc(Pageable pageable);

    // 리뷰 개수 많은순
    @Query("SELECT r FROM RESTAURANT r LEFT JOIN r.reviews rev GROUP BY r.id ORDER BY COUNT(rev) DESC")
    Page<RestaurantEntity> findRestaurantsOrderByReviewCountDesc(Pageable pageable);
}
