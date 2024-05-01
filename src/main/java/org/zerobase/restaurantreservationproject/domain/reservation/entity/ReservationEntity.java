package org.zerobase.restaurantreservationproject.domain.reservation.entity;

import jakarta.persistence.*;
import lombok.*;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;
import org.zerobase.restaurantreservationproject.domain.user.entity.UserEntity;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RESERVATION")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자와 다대일 관계
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // 매니저와 다대일 관계
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private ManagerEntity manager;

    // 레스토랑과 다대일 관계
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    private Integer
}
