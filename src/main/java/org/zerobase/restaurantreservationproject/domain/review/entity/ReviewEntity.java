package org.zerobase.restaurantreservationproject.domain.review.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.util.StringUtils;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;
import org.zerobase.restaurantreservationproject.domain.review.dto.ReviewModification;
import org.zerobase.restaurantreservationproject.domain.user.entity.UserEntity;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "REVIEW")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자와 다대일 관계
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // 레스토랑과 다대일 관계
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    @Digits(integer = 1, fraction = 1)
    private double starRating;

    @Size(max = 100)
    private String comment;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public void modify(ReviewModification.Request request) {
        if (StringUtils.hasText(request.getComment())) {
            this.comment = request.getComment();
        }
        if(request.getStarRating() >= 0 && request.getStarRating() <= 5) {
            this.starRating = request.getStarRating();
        }
    }

}
