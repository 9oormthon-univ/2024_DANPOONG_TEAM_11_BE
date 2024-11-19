package shop.nongdam.nongdambackend.farm.api.dto.request;

public record FarmSaveRequestDTO(
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
