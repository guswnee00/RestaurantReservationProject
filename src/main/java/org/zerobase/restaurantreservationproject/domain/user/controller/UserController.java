package org.zerobase.restaurantreservationproject.domain.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerobase.restaurantreservationproject.domain.user.dto.User;
import org.zerobase.restaurantreservationproject.domain.user.dto.UserDto;
import org.zerobase.restaurantreservationproject.domain.user.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
@ResponseBody
public class UserController {

    private final UserService userService;

    /**
     * 일반 사용자 회원가입
     */
    @PostMapping("/user/signup")
    public ResponseEntity<?> userSignup(@RequestBody User.Request request) {
        UserDto userDto = userService.signup(request);
        return ResponseEntity.ok(User.Response.fromDto(userDto));
    }

}
