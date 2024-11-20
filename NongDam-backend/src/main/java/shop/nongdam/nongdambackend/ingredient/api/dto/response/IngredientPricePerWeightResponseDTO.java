package shop.nongdam.nongdambackend.ingredient.api.dto.response;

import lombok.Builder;
import shop.nongdam.nongdambackend.ingredient.domain.IngredientPricePerWeight;

@Builder
public record IngredientPricePerWeightResponseDTO(
        Long weight,
        Long price
) {
    public static IngredientPricePerWeightResponseDTO from(IngredientPricePerWeight ingredientPricePerWeight){
        return IngredientPricePerWeightResponseDTO.builder()
                .weight(ingredientPricePerWeight.getWeight())
                .price(ingredientPricePerWeight.getPrice())
                .build();
    }
}
