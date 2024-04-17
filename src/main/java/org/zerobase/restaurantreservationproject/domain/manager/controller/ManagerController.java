package org.zerobase.restaurantreservationproject.domain.manager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor

public class ManagerController {

    @PostMapping("/signup")
    public ResponseEntity<?> managerSignup() {
        return ResponseEntity.ok();
    }

    @PreAuthorize("hasRole('WRITE')")
    @PostMapping("/restaurant-registration")
    public ResponseEntity<?> registerRestaurant() {
        return ResponseEntity.ok();
    }

    @PreAuthorize("hasRole('WRITE')")
    @PutMapping("/restaurant-modification/{managerId}")
    public ResponseEntity<?> modifyRestaurant() {
        return ResponseEntity.ok();
    }

}
