package org.zerobase.restaurantreservationproject.domain.manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.restaurantreservationproject.domain.manager.dto.ManagerAddition;
import org.zerobase.restaurantreservationproject.domain.manager.dto.ManagerDto;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;
import org.zerobase.restaurantreservationproject.domain.manager.repository.ManagerRepository;
import org.zerobase.restaurantreservationproject.domain.restaurant.dto.RestaurantAddition;
import org.zerobase.restaurantreservationproject.domain.restaurant.dto.RestaurantDto;
import org.zerobase.restaurantreservationproject.domain.restaurant.dto.RestaurantModification;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;
import org.zerobase.restaurantreservationproject.domain.restaurant.repository.RestaurantRepository;
import org.zerobase.restaurantreservationproject.global.error.CustomException;

import static org.zerobase.restaurantreservationproject.global.error.CustomErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final RestaurantRepository restaurantRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 매니저 회원가입
     *      1. 같은 이름 있는지 확인
     *      2. 비밀번호 encode
     *      3. 매니저 정보 repo 에 저장
     *      4. 매니저 dto 반환
     */
    public ManagerDto signup(ManagerAddition.Request request) {

        // 같은 유저이름(아이디)가 있다면 예외 발생
        if (managerRepository.existsByUsername(request.getUsername())) {
            throw new CustomException(ID_IS_ALREADY_IN_USE);
        }

        // 비밀번호 encode
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        // 매니저 정보 저장
        ManagerEntity managerEntity = managerRepository.save(ManagerAddition.Request.toEntity(request));

        return ManagerDto.toDto(managerEntity);
    }

    /**
     * 매니저의 레스토랑 등록
     *      1. 매니저 request 에서 매니저의 아이디를 추출
     *      2. 매니저가 존재하는지, 매니저가 이미 레스토랑을 등록했는지 확인후
     *      3. 레스토랑 정보를 repo 에 등록
     *      4. 레스토랑 dto 반환
     */
    public RestaurantDto addRestaurant(RestaurantAddition.Request request) {

        // 매니저가 있는지 확인
        this.checkManagerExist(request.getManagerName());
        // 매니저가 이미 레스토랑을 등록했는지 확인
        this.checkRestaurantExist(request.getManagerName());

        RestaurantEntity restaurantEntity = restaurantRepository.save(RestaurantAddition.Request.toEntity(request, managerRepository));

        return RestaurantDto.toDto(restaurantEntity);

    }

    /**
     * 매니저의 레스토랑 정보 수정
     *      1. 레스토랑 정보가 있는지 확인
     *      2. 레스토랑 정보 수정
     *      3. 수정된 정보 repo 에 저장
     *      4. 레스토랑 dto 반환
     */
    public RestaurantDto modifyRestaurant(RestaurantModification.Request request) {

        // 레스토랑 정보가 있는지 확인
        RestaurantEntity oldRestaurantEntity = restaurantRepository.findByManager_Username(request.getManagerName());
        this.checkRestaurantNull(oldRestaurantEntity);

        // 수정
        oldRestaurantEntity.modify(request);

        RestaurantEntity newRestaurantEntity = restaurantRepository.save(oldRestaurantEntity);

        return RestaurantDto.toDto(newRestaurantEntity);

    }

    private void checkManagerExist(String managerName) {
        if(!managerRepository.existsByUsername(managerName)) {
            throw new CustomException(MANAGER_DOES_NOT_EXIST);
        }
    }

    private void checkRestaurantExist(String username) {
        if (restaurantRepository.existsByManager_Username(username)) {
            throw new CustomException(ALREADY_REGISTERED_BY_MANAGER);
        }
    }

    private void checkRestaurantNull(RestaurantEntity entity) {
        if(entity == null) {
            throw new CustomException(RESTAURANT_INFO_DOES_NOT_EXIST);
        }
    }


}
