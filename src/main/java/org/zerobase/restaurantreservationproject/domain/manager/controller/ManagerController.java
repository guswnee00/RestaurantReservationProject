package org.zerobase.restaurantreservationproject.domain.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerobase.restaurantreservationproject.domain.manager.dto.Manager;
import org.zerobase.restaurantreservationproject.domain.manager.dto.ManagerDto;
import org.zerobase.restaurantreservationproject.domain.manager.service.ManagerService;

@RestController
@RequiredArgsConstructor
@ResponseBody
public class ManagerController {

    private final ManagerService managerService;

    /**
     * 매장 점장님 회원가입
     */
    @PostMapping("/manager/signup")
    public ResponseEntity<?> managerSignup(@RequestBody Manager.Request request) {
        ManagerDto managerDto = managerService.signup(request);
        return ResponseEntity.ok(Manager.Response.fromDto(managerDto));
    }

    /*

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/manager/restaurant-registration")
    public ResponseEntity<?> registerRestaurant() {
        return ResponseEntity.ok();
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/manager/restaurant-modification/{managerId}")
    public ResponseEntity<?> modifyRestaurant() {
        return ResponseEntity.ok();
    }

    */

}
