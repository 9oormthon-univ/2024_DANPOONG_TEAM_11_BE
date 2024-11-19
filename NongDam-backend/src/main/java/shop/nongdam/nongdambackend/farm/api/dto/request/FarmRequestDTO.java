package shop.nongdam.nongdambackend.farm.api.dto.request;

public record FarmRequestDTO(
        String farmName,
        String profileImage,
        String farmRepresentative,
        String phoneNumber,
        Long businessRegistrationNumber,
        String address,
        Double latitude,
        Double longitude
) {
}
