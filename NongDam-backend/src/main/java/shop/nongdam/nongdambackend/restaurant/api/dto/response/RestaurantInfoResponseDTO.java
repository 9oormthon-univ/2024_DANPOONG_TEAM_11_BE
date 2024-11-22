package shop.nongdam.nongdambackend.restaurant.api.dto.response;

import lombok.Builder;
import shop.nongdam.nongdambackend.restaurant.domain.Restaurant;

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
        String restaurantImage
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
                .build();
    }
}
