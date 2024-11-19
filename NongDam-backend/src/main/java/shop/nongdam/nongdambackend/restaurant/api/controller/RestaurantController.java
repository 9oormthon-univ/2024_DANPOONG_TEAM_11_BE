package shop.nongdam.nongdambackend.restaurant.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.nongdam.nongdambackend.global.template.ApiResponseTemplate;
import shop.nongdam.nongdambackend.restaurant.api.dto.request.RestaurantSaveRequestDTO;
import shop.nongdam.nongdambackend.restaurant.api.dto.response.RestaurantInfoResponseDTO;
import shop.nongdam.nongdambackend.restaurant.api.dto.response.RestaurantInfoResponseDTOs;
import shop.nongdam.nongdambackend.restaurant.appllication.RestaurantService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    public ApiResponseTemplate<RestaurantInfoResponseDTO> save(@RequestBody RestaurantSaveRequestDTO restaurantSaveRequestDTO) {
        return ApiResponseTemplate.created("식당 등록 성공.", restaurantService.save(restaurantSaveRequestDTO));
    }

    @GetMapping("/{restaurantId}")
    public ApiResponseTemplate<RestaurantInfoResponseDTO> findById(@PathVariable Long restaurantId) {
        return ApiResponseTemplate.ok("식당 상세 조회 성공", restaurantService.findById(restaurantId));
    }

    @GetMapping
    public ApiResponseTemplate<RestaurantInfoResponseDTOs> findAll(@RequestParam(defaultValue = "0", name = "page") int page,
                                                                   @RequestParam(defaultValue = "10", name = "size") int size) {
        return ApiResponseTemplate.ok("식당 전체 조회 성공", restaurantService.findAll(PageRequest.of(page, size)));
    }
}
