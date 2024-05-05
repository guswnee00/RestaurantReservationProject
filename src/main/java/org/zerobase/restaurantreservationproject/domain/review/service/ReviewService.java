package org.zerobase.restaurantreservationproject.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.restaurantreservationproject.domain.reservation.entity.ReservationEntity;
import org.zerobase.restaurantreservationproject.domain.reservation.repository.ReservationRepository;
import org.zerobase.restaurantreservationproject.domain.restaurant.repository.RestaurantRepository;
import org.zerobase.restaurantreservationproject.domain.review.dto.ReviewAddition;
import org.zerobase.restaurantreservationproject.domain.review.dto.ReviewDto;
import org.zerobase.restaurantreservationproject.domain.review.dto.ReviewModification;
import org.zerobase.restaurantreservationproject.domain.review.entity.ReviewEntity;
import org.zerobase.restaurantreservationproject.domain.review.repository.ReviewRepository;
import org.zerobase.restaurantreservationproject.domain.user.repository.UserRepository;
import org.zerobase.restaurantreservationproject.global.enumset.ReservationStatus;
import org.zerobase.restaurantreservationproject.global.error.CustomException;

import static org.zerobase.restaurantreservationproject.global.error.CustomErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final RestaurantRepository restaurantRepository;

    /**
     * 사용자의 리뷰 작성
     *      1. 예약 내역 확인
     *      2. 예약 내역 가져와 리뷰 작성 권한이 있는지 확인
     *      3. 리뷰 repo 에 저장
     *      4. 리뷰 dto 반환
     */
    public ReviewDto addReview(String username, ReviewAddition.Request request) {
        this.checkReservationExist(username);

        ReservationEntity reservationEntity = reservationRepository.findByUser_Username(username);
        this.checkPermissionToWriteReview(reservationEntity, request.getUsername(), request.getRestaurantName());

        ReviewEntity reviewEntity = reviewRepository.save(ReviewAddition.Request.toEntity(request, userRepository, restaurantRepository));

        return ReviewDto.toDto(reviewEntity);
    }

    /**
     * 사용자의 리뷰 수정
     *      1. 리뷰가 존재하는지 확인
     *      2. 리뷰 수정
     *      3. 수정된 리뷰 repo 저장
     *      4. 리뷰 dto 반환
     */
    public ReviewDto modifyReview(String username, ReviewModification.Request request) {
        this.checkReviewExist(username);

        // 수정
        ReviewEntity oldReviewEntity = reviewRepository.findByUser_Username(username);
        oldReviewEntity.modify(request);

        ReviewEntity newReviewEntity = reviewRepository.save(oldReviewEntity);

        return ReviewDto.toDto(newReviewEntity);
    }

    private void checkReservationExist(String username) {
        if(!reservationRepository.existsByUser_Username(username)) {
            throw new CustomException(RESERVATION_DOES_NOT_EXIST);
        }
    }

    private void checkPermissionToWriteReview(ReservationEntity entity, String username, String restaurantName) {
        // 예약자와 작성자가 다른 경우
        if (!entity.getUser().getUsername().equals(username)) {
            throw new CustomException(NO_PERMISSION_TO_WRITE);
        }

        // 이미 작성한 경우
        if (reviewRepository.writeReviewAlready(username, restaurantName)) {
            throw new CustomException(ALREADY_WRITE_FOR_THIS_RESTAURANT);
        }

        // 예약 상태가 이용 종료가 아닌경우
        if (!entity.getStatus().equals(ReservationStatus.CONCLUDE)) {
            throw new CustomException(NO_PERMISSION_TO_WRITE);
        }
    }

    private void checkReviewExist(String username) {
        if (!reviewRepository.existsByUser_Username(username)) {
            throw new CustomException(REVIEW_DOES_NOT_EXIST);
        }
    }

    /**
     * 사용자의 리뷰 삭제
     *      1. 리뷰를 작성한 이용자의 이름과 레스토랑 이름으로 리뷰 선택
     *      2. 리뷰가 있다면 삭제
     *      3. 없다면 예외 발생
     */
    public void deleteReviewByUser(String username, String restaurantName) {
        ReviewEntity reviewEntity = reviewRepository.findByUser_UsernameAndRestaurant_RestaurantName(username, restaurantName);

        if (reviewEntity != null) {
            reviewRepository.delete(reviewEntity);
        } else {
            throw new CustomException(REVIEW_DOES_NOT_EXIST);
        }
    }

    /**
     * 매니저의 리뷰 삭제
     *      1. 레스토랑의 매니저의 이름과 리뷰아이디로 원하는 리뷰 선택
     *      2. 해당 리뷰가 존재한다면 삭제
     *      3. 없다면 예외 발생
     */
    public void deleteReviewByManager(String managerName, Long id) {
        ReviewEntity reviewEntity = reviewRepository.findByRestaurant_Manager_UsernameAndId(managerName, id);

        if (reviewEntity != null) {
            reviewRepository.delete(reviewEntity);
        } else {
            throw new CustomException(REVIEW_DOES_NOT_EXIST);
        }

    }
}
