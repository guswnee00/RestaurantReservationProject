package org.zerobase.restaurantreservationproject.domain.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;
import org.zerobase.restaurantreservationproject.domain.reservation.entity.ReservationEntity;
import org.zerobase.restaurantreservationproject.domain.review.entity.ReviewEntity;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RESTAURANT")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private String managerUsername;

    // 매니저와 일대일 관계(하나의 레스토랑은 한명의 사장님을 가짐 - 레스토랑이 주인엔티티)
    @OneToOne
    @JoinColumn(name = "manager_id")
    private ManagerEntity manager;

    private String restaurantName;
    private String restaurantAddress;
    private String restaurantDetail;

    private double lat;
    private double lnt;

    // 예약과 일대다 관계(하나의 레스토랑은 여러개의 예약을 받을 수 있음 - 예약이 주인엔티티)
    @OneToMany(mappedBy = "restaurant")
    private List<ReservationEntity> reservations;

    // 리뷰와 일대다 관계(하나의 레스토랑은 여러개의 리뷰를 가질 수 있음 - 리뷰가 주인엔티티)
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviews;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

}
