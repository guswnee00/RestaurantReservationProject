package org.zerobase.restaurantreservationproject.domain.manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.restaurantreservationproject.domain.manager.dto.Manager;
import org.zerobase.restaurantreservationproject.domain.manager.dto.ManagerDto;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;
import org.zerobase.restaurantreservationproject.domain.manager.repository.ManagerRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // TODO
    //  - 사용자 exception 으로 교체
    public ManagerDto signup(Manager.Request request) {
        if (managerRepository.existsByManagerId(request.getManagerId())) {
            throw new RuntimeException("이미 존재하는 아이디 입니다.");
        }

        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        ManagerEntity managerEntity = managerRepository.save(Manager.Request.toEntity(request));

        return ManagerDto.toDto(managerEntity);
    }
}
