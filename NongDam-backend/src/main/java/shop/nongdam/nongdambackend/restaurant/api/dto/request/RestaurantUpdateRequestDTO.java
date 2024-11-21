package shop.nongdam.nongdambackend.restaurant.api.dto.request;

public record RestaurantUpdateRequestDTO(
        String restaurantName,
        String restaurantRepresentative,
        String phoneNumber,
        String businessRegistrationNumber,
        String address,
        double latitude,
        double longitude
) {
}
