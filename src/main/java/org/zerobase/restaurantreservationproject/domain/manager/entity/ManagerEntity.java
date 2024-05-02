package org.zerobase.restaurantreservationproject.domain.manager.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.zerobase.restaurantreservationproject.domain.reservation.entity.ReservationEntity;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MANAGER")
@EntityListeners(AuditingEntityListener.class)
public class ManagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;    // 매니저 아이디
    private String password;
    private String phoneNumber;

    private String role;

    // 레스토랑과 일대일 관계(하나의 레스토랑은 한명의 사장님을 가짐 - 레스토랑이 주인엔티티)
    @OneToOne(mappedBy = "manager")
    private RestaurantEntity restaurant;

    // 예약과 일대다 관계(한명의 사장님은 여러개의 예약을 받을 수 있음 - 예약이 주인엔티티)
    @OneToMany(mappedBy = "manager")
    private List<ReservationEntity> reservations;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

}
