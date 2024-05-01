package org.zerobase.restaurantreservationproject.domain.manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.restaurantreservationproject.domain.manager.dto.Manager;
import org.zerobase.restaurantreservationproject.domain.manager.dto.ManagerDto;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;
import org.zerobase.restaurantreservationproject.domain.manager.repository.ManagerRepository;
import org.zerobase.restaurantreservationproject.global.error.CustomException;

import static org.zerobase.restaurantreservationproject.global.error.CustomErrorCode.ID_IS_ALREADY_IN_USE;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ManagerDto signup(Manager.Request request) {

        // 같은 유저이름(아이디)가 있다면 예외 발생
        if (managerRepository.existsByUsername(request.getUsername())) {
            throw new CustomException(ID_IS_ALREADY_IN_USE);
        }

        // 비밀번호 encode
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        // 사용자 정보 저장
        ManagerEntity managerEntity = managerRepository.save(Manager.Request.toEntity(request));

        return ManagerDto.toDto(managerEntity);
    }

}
