package org.zerobase.restaurantreservationproject.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
    private final int statusCode;
    private final String description;


}
