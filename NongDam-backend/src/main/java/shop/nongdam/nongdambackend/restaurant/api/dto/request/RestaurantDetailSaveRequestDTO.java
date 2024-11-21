package shop.nongdam.nongdambackend.restaurant.api.dto.request;

import shop.nongdam.nongdambackend.restaurant.menu.api.dto.request.MenuSaveRequestDTOs;

public record RestaurantDetailSaveRequestDTO(
        Long restaurantId,
        double latitude,
        double longitude,
        String openTime,
        String closeTime
) {
}
