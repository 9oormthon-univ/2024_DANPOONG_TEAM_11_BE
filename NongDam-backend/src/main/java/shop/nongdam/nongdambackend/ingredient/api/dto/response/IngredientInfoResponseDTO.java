package shop.nongdam.nongdambackend.ingredient.api.dto.response;

import lombok.Builder;
import shop.nongdam.nongdambackend.farm.api.dto.response.FarmSummaryResponseDTO;
import shop.nongdam.nongdambackend.ingredient.domain.Ingredient;

import java.util.List;

@Builder
public record IngredientInfoResponseDTO(
        Long ingredientId,
        String ingredientName,
        FarmSummaryResponseDTO farmSummaryResponseDTO,
        String uglyReason,
        String ingredientDescription
) {
    public static IngredientInfoResponseDTO from(Ingredient ingredient){
        return IngredientInfoResponseDTO.builder()
                .ingredientId(ingredient.getId())
                .ingredientName(ingredient.getIngredientName())
                .farmSummaryResponseDTO(FarmSummaryResponseDTO.from(ingredient.getFarm()))
                .uglyReason(ingredient.getIngredientUglyReason().getName())
                .ingredientDescription(ingredient.getIngredientDescription())
                .build();
    }
}
