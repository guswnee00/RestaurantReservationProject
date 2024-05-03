package org.zerobase.restaurantreservationproject.domain.restaurant.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.global.enumset.SortRestaurant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RestaurantList {

    private String restaurantName;
    private SortRestaurant sort;
    private double distance;

}
