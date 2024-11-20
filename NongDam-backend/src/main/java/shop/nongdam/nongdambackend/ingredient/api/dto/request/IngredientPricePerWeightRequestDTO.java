package shop.nongdam.nongdambackend.ingredient.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record IngredientPricePerWeightRequestDTO(
        @NotBlank(message = "식료품 무게는 필수 입력값입니다.")
        Long weight,
        @NotBlank(message = "식료품 가격은 필수 입력값입니다.")
        Long price
) {
}
