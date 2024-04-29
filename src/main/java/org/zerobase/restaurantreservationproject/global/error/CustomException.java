package org.zerobase.restaurantreservationproject.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException{
    private CustomErrorCode customErrorCode;
    private String errorMessage;

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getDescription());
        this.customErrorCode = customErrorCode;
        this.errorMessage = customErrorCode.getDescription();
    }
}
