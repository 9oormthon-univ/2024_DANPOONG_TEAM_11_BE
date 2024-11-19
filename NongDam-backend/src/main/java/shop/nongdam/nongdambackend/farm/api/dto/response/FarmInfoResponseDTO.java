package shop.nongdam.nongdambackend.farm.api.dto.response;

import lombok.Builder;
import shop.nongdam.nongdambackend.farm.domain.Farm;

@Builder
public record FarmInfoResponseDTO(
        Farm farm
){
    public static FarmInfoResponseDTO from(Farm farm){
        return FarmInfoResponseDTO.builder()
                .farm(farm)
                .build();

    }
}
