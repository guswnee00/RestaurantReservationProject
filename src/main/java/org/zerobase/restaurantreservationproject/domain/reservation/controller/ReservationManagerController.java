package org.zerobase.restaurantreservationproject.domain.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;
import org.zerobase.restaurantreservationproject.domain.reservation.dto.ReservationDto;
import org.zerobase.restaurantreservationproject.domain.reservation.dto.ReservationStatusChange;
import org.zerobase.restaurantreservationproject.domain.reservation.service.ReservationService;
import org.zerobase.restaurantreservationproject.global.enumset.ReservationStatus;
import org.zerobase.restaurantreservationproject.global.error.CustomException;

import static org.zerobase.restaurantreservationproject.global.error.CustomErrorCode.MANAGER_NAME_MISMATCH;

@RestController
@RequiredArgsConstructor
@ResponseBody
public class ReservationManagerController {

    private final ReservationService reservationService;

    /**
     * 예약 상태 변경
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/manager/reservation/{managerName}")
    public ResponseEntity<?> changeReservationStatus(@PathVariable String managerName,
                                                     @RequestBody ReservationStatusChange statusChange,
                                                     @AuthenticationPrincipal ManagerEntity manager
    ) {
        String currentManagerName = manager.getUsername();
        if(!managerName.equals(currentManagerName)) {
            throw new CustomException(MANAGER_NAME_MISMATCH);
        }
        reservationService.changeReservationStatus(managerName, ReservationStatus.of(statusChange.getStatus()));
        return ResponseEntity.ok(reservationService.reservationInformationByManager(managerName));
    }

    /**
     * 예약 정보 확인
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/manager/reservation/{managerName}/detail")
    public ResponseEntity<?> reservationDetail(@PathVariable String managerName,
                                               @AuthenticationPrincipal ManagerEntity manager
    ) {
        String currentManagerName = manager.getUsername();
        if(!managerName.equals(currentManagerName)) {
            throw new CustomException(MANAGER_NAME_MISMATCH);
        }
        ReservationDto reservationDto = reservationService.reservationInformationByManager(managerName);
        return ResponseEntity.ok(reservationDto);
    }
}
