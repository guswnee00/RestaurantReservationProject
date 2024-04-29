package org.zerobase.restaurantreservationproject.domain.manager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.zerobase.restaurantreservationproject.domain.manager.dto.Manager;
import org.zerobase.restaurantreservationproject.domain.manager.dto.ManagerDto;
import org.zerobase.restaurantreservationproject.domain.manager.service.ManagerService;

@Slf4j
@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    /**
     * 매장 점장님 회원가입
     */
    @PostMapping("/signup")
    public ResponseEntity<?> managerSignup(@RequestBody Manager.Request request) {
        ManagerDto managerDto = managerService.signup(request);
        return ResponseEntity.ok(Manager.Response.fromDto(managerDto));
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/restaurant-registration")
    public ResponseEntity<?> registerRestaurant() {
        return ResponseEntity.ok();
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/restaurant-modification/{managerId}")
    public ResponseEntity<?> modifyRestaurant() {
        return ResponseEntity.ok();
    }

}
