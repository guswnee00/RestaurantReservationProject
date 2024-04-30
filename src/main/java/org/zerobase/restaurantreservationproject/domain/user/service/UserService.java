package org.zerobase.restaurantreservationproject.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.restaurantreservationproject.domain.user.dto.User;
import org.zerobase.restaurantreservationproject.domain.user.dto.UserDto;
import org.zerobase.restaurantreservationproject.domain.user.entity.UserEntity;
import org.zerobase.restaurantreservationproject.domain.user.repository.UserRepository;
import org.zerobase.restaurantreservationproject.global.error.CustomException;

import static org.zerobase.restaurantreservationproject.global.error.CustomErrorCode.ID_IS_ALREADY_IN_USE;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto signup(User.Request request) {
        if (userRepository.existsByUserId(request.getUserId())) {
            throw new CustomException(ID_IS_ALREADY_IN_USE);
        }

        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        UserEntity userEntity = userRepository.save(User.Request.toEntity(request));

        return UserDto.toDto(userEntity);
    }
}
