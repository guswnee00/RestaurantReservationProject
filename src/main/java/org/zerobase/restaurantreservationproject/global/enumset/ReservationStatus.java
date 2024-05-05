package org.zerobase.restaurantreservationproject.global.enumset;

import org.springframework.util.StringUtils;
import org.zerobase.restaurantreservationproject.global.error.CustomException;

import static org.zerobase.restaurantreservationproject.global.error.CustomErrorCode.EMPTY_RESERVATION_STATUS_CODE;
import static org.zerobase.restaurantreservationproject.global.error.CustomErrorCode.NON_EXIST_RESERVATION_STATUS_CODE;

public enum ReservationStatus {
    REQUEST,    // 요청
    REFUSAL,    // 거절
    CONFIRM,    // 확정
    ARRIVAL,    // 도착
    NO_SHOW,    // 노쇼
    CONCLUDE;    // 이용 완료

    public static ReservationStatus of(String status) {
        status = status.toUpperCase();
        if (!StringUtils.hasText(status)) {
            throw new CustomException(EMPTY_RESERVATION_STATUS_CODE);
        }
        for (ReservationStatus reservationStatus: ReservationStatus.values()) {
            if (reservationStatus.toString().equals(status)){
                return reservationStatus;
            }
        }
        throw new CustomException(NON_EXIST_RESERVATION_STATUS_CODE);
    }

}
