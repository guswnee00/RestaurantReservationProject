package org.zerobase.restaurantreservationproject.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
    // 회원가입
    ID_IS_ALREADY_IN_USE(HttpStatus.CONFLICT.value(), "이미 사용중인 아이디 입니다."),

    // 유저
    USER_DOES_NOT_EXIST(HttpStatus.BAD_REQUEST.value(), "해당 유저는 존재하지 않습니다."),

    // 매니저
    MANAGER_DOES_NOT_EXIST(HttpStatus.BAD_REQUEST.value(), "해당 매니저는 존재하지 않습니다."),
    ALREADY_REGISTERED_BY_MANAGER(HttpStatus.CONFLICT.value(), "해당 매니저는 이미 레스토랑을 등록했습니다."),
    MANAGER_DOES_NOT_SAME(HttpStatus.CONFLICT.value(), "매니저 아이디가 일치하지 않습니다."),

    // 레스토랑
    RESTAURANT_DOES_NOT_EXIST(HttpStatus.BAD_REQUEST.value(), "해당 레스토랑은 존재하지 않습니다."),
    RESTAURANT_NOT_REGISTERED(HttpStatus.BAD_REQUEST.value(), "아직 등록된 레스토랑이 없습니다."),
    RESTAURANT_INFO_DOES_NOT_EXIST(HttpStatus.BAD_REQUEST.value(), "해당 레스토랑 정보가 존재하지 않습니다."),

    // 리뷰
    NO_PERMISSION_TO_WRITE(HttpStatus.FORBIDDEN.value(), "해당 리뷰에 대해 작성 권한이 없습니다."),
    ALREADY_WRITE_FOR_THIS_RESTAURANT(HttpStatus.CONFLICT.value(), "해당 레스토랑에 대해 이미 리뷰를 작성했습니다."),
    REVIEW_DOES_NOT_EXIST(HttpStatus.BAD_REQUEST.value(), "해당 리뷰가 존재하지 않습니다."),
    USERNAME_MISMATCH(HttpStatus.FORBIDDEN.value(), "유저의 아이디가 일치하지 않습니다."),

    // 예약
    RESERVATION_DOES_NOT_EXIST(HttpStatus.BAD_REQUEST.value(), "해당 예약 내역이 존재하지 않습니다."),
    NO_PERMISSION_TO_READ(HttpStatus.FORBIDDEN.value(), "해당 예약에 대해 읽기 원한이 없습니다."),
    RESERVATION_USERNAME_MISMATCH(HttpStatus.CONFLICT.value(), "예약 내역의 아이디와 일치하지 않습니다."),
    PHONE_NUMBER_MISMATCH(HttpStatus.CONFLICT.value(), "예약 내역의 전화번호와 일치하지 않습니다."),
    RESERVATION_STATUS_IS_INCORRECT(HttpStatus.CONFLICT.value(), "예약 상태가 올바르지 않습니다."),
    ARRIVAL_CHECKIN_TIMEOUT(HttpStatus.CONFLICT.value(), "예약 시간 10분 전에 도착을 완료했어야 합니다."),
    MANAGER_NAME_MISMATCH(HttpStatus.FORBIDDEN.value(), "매니저의 아이디가 일치하지 않습니다."),
    EMPTY_RESERVATION_STATUS_CODE(HttpStatus.BAD_REQUEST.value(), "예약 상태코드가 존재하지 않습니다."),
    NON_EXIST_RESERVATION_STATUS_CODE(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 상태코드입니다."),
    ;

    private final int statusCode;
    private final String description;
}
