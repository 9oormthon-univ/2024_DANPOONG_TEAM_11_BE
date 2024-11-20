package shop.nongdam.nongdambackend.ingredient.api.dto.request;

import shop.nongdam.nongdambackend.ingredient.domain.IngredientPricePerWeight;

import java.util.List;

public record IngredientPricePerWeightRequestDTOs(
        List<IngredientPricePerWeightRequestDTO> ingredientPricePerWeightRequestDTOs
) {
    public static List<IngredientPricePerWeight> mapToIngredientPricePerWeights(
            IngredientPricePerWeightRequestDTOs requestDTOs
    ) {
        return requestDTOs.ingredientPricePerWeightRequestDTOs()
                .stream()
                .map(dto -> IngredientPricePerWeight.builder()
                        .weight(dto.weight())
                        .price(dto.price())
                        .build()
                )
                .toList();
    }

}
