package org.zerobase.restaurantreservationproject.domain.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.zerobase.restaurantreservationproject.domain.manager.dto.ManagerAddition;
import org.zerobase.restaurantreservationproject.domain.manager.dto.ManagerDto;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;
import org.zerobase.restaurantreservationproject.domain.manager.service.ManagerService;
import org.zerobase.restaurantreservationproject.domain.restaurant.dto.RestaurantAddition;
import org.zerobase.restaurantreservationproject.domain.restaurant.dto.RestaurantDto;
import org.zerobase.restaurantreservationproject.domain.restaurant.dto.RestaurantModification;
import org.zerobase.restaurantreservationproject.global.error.CustomException;

import static org.zerobase.restaurantreservationproject.global.error.CustomErrorCode.MANAGER_DOES_NOT_SAME;

@RestController
@RequiredArgsConstructor
@ResponseBody
public class ManagerController {

    private final ManagerService managerService;

    /**
     * 매니저 회원가입
     */
    @PostMapping("/signup/manager")
    public ResponseEntity<?> managerSignup(@RequestBody ManagerAddition.Request request) {
        ManagerDto managerDto = managerService.signup(request);
        return ResponseEntity.ok(ManagerAddition.Response.fromDto(managerDto));
    }

    /**
     * 매니저의 매장 등록
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/manager/restaurant-registration/{username}")
    public ResponseEntity<?> registerRestaurant(@PathVariable String username,
                                                @RequestBody RestaurantAddition.Request request,
                                                @AuthenticationPrincipal ManagerEntity manager
    ) {
        if(!username.equals(manager.getUsername())) {
            throw new CustomException(MANAGER_DOES_NOT_SAME);
        }
        RestaurantDto restaurantDto = managerService.addRestaurant(request);
        return ResponseEntity.ok(RestaurantAddition.Response.fromDto(restaurantDto));
    }

    /**
     * 매니저의 매장 수정
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/manager/restaurant-modification/{username}")
    public ResponseEntity<?> modifyRestaurant(@PathVariable String username,
                                              @RequestBody RestaurantModification.Request request,
                                              @AuthenticationPrincipal ManagerEntity manager
    ) {
        if(!username.equals(manager.getUsername())) {
            throw new CustomException(MANAGER_DOES_NOT_SAME);
        }
        RestaurantDto restaurantDto = managerService.modifyRestaurant(request);
        return ResponseEntity.ok(RestaurantModification.Response.fromDto(restaurantDto));
    }

}
