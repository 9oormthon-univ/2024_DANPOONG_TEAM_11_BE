package shop.nongdam.nongdambackend.restaurant.api.dto.request;

import shop.nongdam.nongdambackend.restaurant.menu.api.dto.request.MenuSaveRequestDTOs;

public record RestaurantUpdateRequestDTO(
        String restaurantName,
        String restaurantRepresentative,
        String phoneNumber,
        String businessRegistrationNumber,
        String address,
        double latitude,
        double longitude,
        MenuSaveRequestDTOs menuSaveRequestDTOs
) {
}
