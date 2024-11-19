package shop.nongdam.nongdambackend.farm.api.dto.response;

import lombok.Builder;
import shop.nongdam.nongdambackend.farm.domain.Farm;

@Builder
public record FarmRegistrationResponseDTO (
        Farm farm
){
    public static FarmRegistrationResponseDTO from(Farm farm){
        return FarmRegistrationResponseDTO.builder()
                .farm(farm)
                .build();

    }
}
