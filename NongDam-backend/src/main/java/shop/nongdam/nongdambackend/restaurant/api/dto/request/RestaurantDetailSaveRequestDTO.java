package shop.nongdam.nongdambackend.restaurant.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RestaurantDetailSaveRequestDTO(
        @NotBlank(message = "식당 이름은 필수 입력값입니다.")
        Long restaurantId,

        @NotBlank(message = "위도는 필수 입력값입니다.")
        double latitude,

        @NotBlank(message = "경도 필수 입력값입니다.")
        double longitude,

        @NotBlank(message = "오픈 시간은 필수 입력값입니다.")
        String openTime,

        @NotBlank(message = "마감 시간은 필수 입력값입니다.")
        String closeTime
) {
}
