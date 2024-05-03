package org.zerobase.restaurantreservationproject.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.restaurantreservationproject.domain.user.dto.UserAddition;
import org.zerobase.restaurantreservationproject.domain.user.dto.UserDto;
import org.zerobase.restaurantreservationproject.domain.user.entity.UserEntity;
import org.zerobase.restaurantreservationproject.domain.user.repository.UserRepository;
import org.zerobase.restaurantreservationproject.global.error.CustomException;

import static org.zerobase.restaurantreservationproject.global.error.CustomErrorCode.ID_IS_ALREADY_IN_USE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 유저 회원가입
     *      1. 같은 이름 있는지 확인
     *      2. 비밀번호 encode
     *      3. 유저 정보 repo 에 저장
     *      4. 유저 dto 반환
     */
    public UserDto signup(UserAddition.Request request) {

        // 같은 유저이름(아이디)가 있다면 예외 발생
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new CustomException(ID_IS_ALREADY_IN_USE);
        }

        // 비밀번호 encode
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        // 사용자 정보 저장
        UserEntity userEntity = userRepository.save(UserAddition.Request.toEntity(request));

        return UserDto.toDto(userEntity);
    }

}
