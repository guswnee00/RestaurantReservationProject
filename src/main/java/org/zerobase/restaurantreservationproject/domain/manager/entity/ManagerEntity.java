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
    private String managerId;
    private  String password;

    private String managerName;
    private String phoneNumber;

    private String role;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurantId;
    //private String restaurantName;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
