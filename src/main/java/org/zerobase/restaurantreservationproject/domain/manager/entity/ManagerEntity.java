package org.zerobase.restaurantreservationproject.domain.manager.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;

import java.time.LocalDateTime;

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

    @OneToOne(mappedBy = "manager")
    private RestaurantEntity restaurant;   // 레스토랑과 일대일 관계(하나의 레스토랑은 한명의 사장님을 가짐)

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

}
