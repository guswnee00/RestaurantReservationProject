package org.zerobase.restaurantreservationproject.domain.restaurant.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerobase.restaurantreservationproject.domain.restaurant.dto.RestaurantDetail;
import org.zerobase.restaurantreservationproject.domain.restaurant.dto.RestaurantSortingMethod;
import org.zerobase.restaurantreservationproject.domain.restaurant.service.RestaurantService;

@RestController
@RequiredArgsConstructor
@ResponseBody
public class RestaurantController {

    private final RestaurantService restaurantService;

    /**
     * 레스토랑에 대한 상세 정보 보기
     */
    @GetMapping("/restaurant/{restaurantName}")
    public ResponseEntity<?> getRestaurantDetail(@PathVariable String restaurantName) {
        RestaurantDetail restaurantDetail = restaurantService.getRestaurantDetailByRestaurantName(restaurantName);
        return ResponseEntity.ok(restaurantDetail);
    }

    /**
     * 정렬 방법 별 레스토랑 리스트
     */
    @GetMapping("/restaurant/list")
    public ResponseEntity<?> getRestaurantList(@RequestParam RestaurantSortingMethod method,
                                               @RequestParam(defaultValue = "1") int page
    ) {
        Page<RestaurantDetail> restaurantDetailPage = restaurantService.getRestaurantPage(method, page);
        return ResponseEntity.ok(restaurantDetailPage);
    }

}
