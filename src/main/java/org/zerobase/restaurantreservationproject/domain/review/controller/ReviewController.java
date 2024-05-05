package org.zerobase.restaurantreservationproject.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.zerobase.restaurantreservationproject.domain.review.dto.ReviewAddition;
import org.zerobase.restaurantreservationproject.domain.review.dto.ReviewDto;
import org.zerobase.restaurantreservationproject.domain.review.dto.ReviewModification;
import org.zerobase.restaurantreservationproject.domain.review.service.ReviewService;
import org.zerobase.restaurantreservationproject.domain.user.entity.UserEntity;
import org.zerobase.restaurantreservationproject.global.error.CustomException;

import static org.zerobase.restaurantreservationproject.global.error.CustomErrorCode.USERNAME_MISMATCH;

@RestController
@RequiredArgsConstructor
@ResponseBody
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 사용자의 리뷰 작성
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/review/registration/{username}")
    public ResponseEntity<?> registerReview(@PathVariable String username,
                                            @RequestBody ReviewAddition.Request request,
                                            @AuthenticationPrincipal UserEntity user
    ) {
        String currentUsername = user.getUsername();
        if(!username.equals(currentUsername)) {
            throw new CustomException(USERNAME_MISMATCH);
        }
        ReviewDto reviewDto = reviewService.addReview(username, request);
        return ResponseEntity.ok(ReviewAddition.Response.fromDto(reviewDto));
    }

    /**
     * 사용자의 리뷰 수정
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/review/modification/{username}")
    public ResponseEntity<?> modifyReview(@PathVariable String username,
                                          @RequestBody ReviewModification.Request request,
                                          @AuthenticationPrincipal UserEntity user
    ) {
        String currentUsername = user.getUsername();
        if(!username.equals(currentUsername)) {
            throw new CustomException(USERNAME_MISMATCH);
        }
        ReviewDto reviewDto = reviewService.modifyReview(username, request);
        return ResponseEntity.ok(ReviewModification.Response.fromDto(reviewDto));
    }


    /**
     * 사용자의 리뷰 삭제
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/review/delete/{username}/{restaurantName}")
    public ResponseEntity<?> deleteReviewByUser(@PathVariable String username,
                                                @PathVariable String restaurantName
    ) {
        reviewService.deleteReviewByUser(username, restaurantName);
        return ResponseEntity.ok("Review deleted successfully.");
    }

    /**
     * 매니저의 리뷰 삭제
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @DeleteMapping("/review/delete/{managerName}/{id}")
    public ResponseEntity<?> deleteReviewByManager(@PathVariable String managerName,
                                                   @PathVariable Long id
    ) {
        reviewService.deleteReviewByManager(managerName, id);
        return ResponseEntity.ok("Review deleted successfully.");
    }
}
