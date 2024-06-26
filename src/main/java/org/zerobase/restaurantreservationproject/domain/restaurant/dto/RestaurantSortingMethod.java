package org.zerobase.restaurantreservationproject.domain.restaurant.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.global.enumset.SortRestaurant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RestaurantSortingMethod {

    private String restaurantName;

    private SortRestaurant sort;

    private double lat;
    private double lnt;

}
