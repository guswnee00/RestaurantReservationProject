package org.zerobase.restaurantreservationproject.domain.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RESTAURANT")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne(mappedBy = "restaurantId")
    private ManagerEntity id;

    private String restaurantName;
    private String restaurantAddress;
    private String restaurantDetail;

    private double lat;
    private double lnt;

    private double reviewGrade;
    private Long reviewAmount;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
