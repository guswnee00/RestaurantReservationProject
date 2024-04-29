package org.zerobase.restaurantreservationproject.global.error;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomExceptionResponse {
    private int statusCode;
    private CustomErrorCode customErrorCode;
    private String errorMessage;

    public CustomExceptionResponse(CustomErrorCode errorCode) {
        this.statusCode = errorCode.getStatusCode();
        this.customErrorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
