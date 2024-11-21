package shop.nongdam.nongdambackend.restaurant.api.dto.response;

import lombok.Builder;
import shop.nongdam.nongdambackend.restaurant.domain.Restaurant;
import shop.nongdam.nongdambackend.restaurant.menu.api.dto.response.MenuInfoResponseDTOs;

@Builder
public record RestaurantInfoResponseDTO(
        Long restaurantId,
        String restaurantName,
        String restaurantRepresentative,
        String phoneNumber,
        String businessRegistrationNumber,
        String address,
        double latitude,
        double longitude,
        String openTime,
        String closeTime,
        String restaurantImage,
        MenuInfoResponseDTOs menuInfoResponseDTOs
) {

    public static RestaurantInfoResponseDTO from(Restaurant restaurant) {
        return RestaurantInfoResponseDTO.builder()
                .restaurantId(restaurant.getId())
                .restaurantName(restaurant.getRestaurantName())
                .restaurantRepresentative(restaurant.getRestaurantRepresentative())
                .phoneNumber(restaurant.getPhoneNumber())
                .businessRegistrationNumber(restaurant.getBusinessRegistrationNumber())
                .address(restaurant.getAddress())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .openTime(restaurant.getOpenTime())
                .closeTime(restaurant.getCloseTime())
                .restaurantImage(restaurant.getRestaurantImage())
                .menuInfoResponseDTOs(MenuInfoResponseDTOs.from(restaurant.getMenu()))
                .build();
    }
}
