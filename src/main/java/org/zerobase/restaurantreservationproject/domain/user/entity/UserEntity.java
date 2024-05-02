package org.zerobase.restaurantreservationproject.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.zerobase.restaurantreservationproject.domain.reservation.entity.ReservationEntity;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USER")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;    // 사용자 아이디
    private String password;
    private String phoneNumber;

    private String role;

    // 리뷰와 일대다 관계(사용자는 여러개의 예약을 만들 수 있음 - 예약이 주인엔티티)
    @OneToMany(mappedBy = "user")
    private List<ReservationEntity> reservations;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

}
