package org.zerobase.restaurantreservationproject.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.restaurantreservationproject.domain.reservation.dto.ReservationAddition;
import org.zerobase.restaurantreservationproject.domain.reservation.dto.ReservationDto;
import org.zerobase.restaurantreservationproject.domain.reservation.entity.ReservationEntity;
import org.zerobase.restaurantreservationproject.domain.reservation.repository.ReservationRepository;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;
import org.zerobase.restaurantreservationproject.domain.restaurant.repository.RestaurantRepository;
import org.zerobase.restaurantreservationproject.domain.user.entity.UserEntity;
import org.zerobase.restaurantreservationproject.domain.user.repository.UserRepository;
import org.zerobase.restaurantreservationproject.global.enumset.ReservationStatus;
import org.zerobase.restaurantreservationproject.global.error.CustomException;

import java.time.LocalDateTime;

import static org.zerobase.restaurantreservationproject.global.error.CustomErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    /**
     * 유저의 레스토랑 예약
     *      1. 레스토랑 예약에 필요한 정보를 입력받아서 entity 로 변환
     *          a. 있는 유저인지, 존재하는 레스토랑인지 확인
     *      2. repo 저장
     *      3. dto 변환
     */
    public ReservationDto addReservation(ReservationAddition.Request request) {
        ReservationEntity reservationEntity = this.reservationRequestToEntity(request);
        reservationRepository.save(reservationEntity);
        return ReservationDto.toDto(reservationEntity);
    }

    private ReservationEntity reservationRequestToEntity(ReservationAddition.Request request) {
        UserEntity userEntity = this.checkUserExist(request.getUsername());
        RestaurantEntity restaurantEntity = this.checkRestaurantExist(request.getRestaurantName());
        LocalDateTime reservationDateTime = LocalDateTime.of(request.getReservationDate(), request.getReservationTime());

        return ReservationEntity.builder()
                .user(userEntity)
                .restaurant(restaurantEntity)
                .headCount(request.getHeadCount())
                .status(ReservationStatus.REQUEST)
                .reservationDateTime(reservationDateTime)
                .build();
    }

    private UserEntity checkUserExist(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new CustomException(USER_DOES_NOT_EXIST);
        }
        return userEntity;
    }

    private RestaurantEntity checkRestaurantExist(String restaurantName) {
        RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantName(restaurantName);
        if (restaurantEntity == null) {
            throw new CustomException(RESTAURANT_DOES_NOT_EXIST);
        }
        return restaurantEntity;
    }

    /**
     * 유저의 예약 정보 확인
     *      1. 유저이름으로 된 예약 정보가 있는지 확인
     *      2. 예약 내역 확인 가능 여부 체크
     *      3. dto 변환
     */
    public ReservationDto reservationInformationByUser(String username) {
        ReservationEntity reservationEntity = this.checkReservationExistByUser(username);

        if (this.checkPermissionToReadReservation(username, reservationEntity)) {
            throw new CustomException(NO_PERMISSION_TO_READ);
        }
        return ReservationDto.toDto(reservationEntity);
    }

    private ReservationEntity checkReservationExistByUser(String username) {
        ReservationEntity reservationEntity = reservationRepository.findByUser_Username(username);
        if (reservationEntity == null) {
            throw new CustomException(RESERVATION_DOES_NOT_EXIST);
        }
        return reservationEntity;
    }

    private boolean checkPermissionToReadReservation(String name, ReservationEntity entity) {
        if (entity.getUser().getUsername().equals(name)) {
            return false;
        } else if (entity.getManager().getUsername().equals(name)) {
            return false;
        }
        return true;
    }

    /**
     * 매니저의 예약 정보 확인
     *      1. 매니저의 앞으로 생긴 예약이 존재하는지 확인
     *      2. 예약 내역 확인 가능 여부 체크
     *      3. dto 변환
     */
    public ReservationDto reservationInformationByManager(String managerName) {
        ReservationEntity reservationEntity = this.checkReservationExistByManager(managerName);

        if (this.checkPermissionToReadReservation(managerName, reservationEntity)) {
            throw new CustomException(NO_PERMISSION_TO_READ);
        }
        return ReservationDto.toDto(reservationEntity);
    }

    private ReservationEntity checkReservationExistByManager(String managerName) {
        ReservationEntity reservationEntity = reservationRepository.findByManager_Username(managerName);
        if (reservationEntity == null) {
            throw new CustomException(RESERVATION_DOES_NOT_EXIST);
        }
        return reservationEntity;
    }

    /**
     * 유저의 도착 확인
     *      1. 본인의 예약 내역이 존재하는지 확인
     *      2. 예약 확인
     *          a. 본인의 이름이 맞는지
     *          b. 본인의 전화번호가 맞는지
     *          c. 예약 상태가 '확정' 상태인지
     *          d. 예약 시간 10분 전에 도착했는지
     *      3. 확인이 끝났으면 예약 상태를 '도착'으로 변경
     *      4. dto 반환
     */
    public ReservationDto checkUserArrival(String username, String phoneNumber) {
        ReservationEntity reservationEntity = this.checkReservationExistByUser(username);

        this.checkArrivalInTime(username, phoneNumber, reservationEntity);
        reservationEntity.setStatus(ReservationStatus.ARRIVAL);
        reservationRepository.save(reservationEntity);
        return ReservationDto.toDto(reservationEntity);
    }

    private void checkArrivalInTime(String username, String inputPhoneNumber, ReservationEntity entity) {
        if (!entity.getUser().getUsername().equals(username)) {
            throw new CustomException(PHONE_NUMBER_MISMATCH);

        } else if (!entity.getUser().getPhoneNumber().equals(inputPhoneNumber)) {
            throw new CustomException(PHONE_NUMBER_MISMATCH);

        } else if (!entity.getStatus().equals(ReservationStatus.CONFIRM)) {
            throw new CustomException(RESERVATION_STATUS_IS_INCORRECT);

        } else if (LocalDateTime.now().isAfter(entity.getReservationDateTime().minusMinutes(10L))) {
            throw new CustomException(ARRIVAL_CHECKIN_TIMEOUT);
        }
    }

    /**
     * 매니저의 예약 상태 변경
     *      1. 매니저 앞으로 된 예약 내역인지 확인
     *      2. 예약 상태 변경(ReservationStatus 에 있는 상태 중 하나)
     *      3. repo 저장
     */
    public void changeReservationStatus(String managerName, ReservationStatus status) {
        ReservationEntity reservationEntity = this.checkReservationExistByManager(managerName);

        reservationEntity.setStatus(status);
        reservationRepository.save(reservationEntity);
    }

}
