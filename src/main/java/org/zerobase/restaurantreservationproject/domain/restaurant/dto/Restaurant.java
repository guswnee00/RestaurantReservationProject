package org.zerobase.restaurantreservationproject.domain.restaurant.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Restaurant {

    private String restaurantName;
    private String restaurantAddress;
    private String restaurantDetail;

    private String distance;
    private String starRating;
    private Long ratingCount;

}
