package shop.nongdam.nongdambackend.restaurant.api.dto.response;

import shop.nongdam.nongdambackend.restaurant.domain.Restaurant;
import shop.nongdam.nongdambackend.restaurant.menu.api.dto.response.MenuInfoResponseDTOs;

public record RestaurantDetailInfoResponseDTO(
        Long restaurantId,
        String restaurantName,
        String restaurantRepresentative,
        String phoneNumber,
        String businessRegistrationNumber,
        String address,
        Double latitude,
        Double longitude,
        MenuInfoResponseDTOs menuInfoResponseDTOs
) {
    public static RestaurantDetailInfoResponseDTO from(Restaurant restaurant) {
        return new RestaurantDetailInfoResponseDTO(
                restaurant.getId(),
                restaurant.getRestaurantName(),
                restaurant.getRestaurantRepresentative(),
                restaurant.getPhoneNumber(),
                restaurant.getBusinessRegistrationNumber(),
                restaurant.getAddress(),
                restaurant.getLatitude(),
                restaurant.getLongitude(),
                MenuInfoResponseDTOs.from(restaurant.getMenu())
        );
    }
}
