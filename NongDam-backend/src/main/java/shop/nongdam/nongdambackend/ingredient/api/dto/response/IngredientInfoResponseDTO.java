package shop.nongdam.nongdambackend.ingredient.api.dto.response;

import lombok.Builder;
import shop.nongdam.nongdambackend.farm.api.dto.response.FarmSummaryResponseDTO;
import shop.nongdam.nongdambackend.farm.domain.Farm;
import shop.nongdam.nongdambackend.ingredient.domain.Ingredient;

import java.util.List;

@Builder
public record IngredientInfoResponseDTO(
        Long ingredientId,
        String ingredientName,
        FarmSummaryResponseDTO farmSummaryResponseDTO,
        String uglyReason,
        String uglyReasonDescription,
        //todo tag
        String ingredientDescription,
        List<IngredientPricePerWeightResponseDTO> ingredientPricePerWeightResponseDTOs
) {
    public static IngredientInfoResponseDTO from(Farm farm, Ingredient ingredient){
//        List<IngredientPricePerWeightResponseDTO> ingredientPricePerWeightResponseDTOs = ingredient
//                .getIngredientPricePerWeights()
//                .stream()
//                .map(IngredientPricePerWeightResponseDTO::from)
//                .toList();


        return IngredientInfoResponseDTO.builder()
                .ingredientId(ingredient.getId())
                .ingredientName(ingredient.getIngredientName())
                .farmSummaryResponseDTO(FarmSummaryResponseDTO.from(farm))
                .uglyReason(ingredient.getIngredientUglyReason().getName())
                .uglyReasonDescription(ingredient.getUglyDescription())
                .ingredientDescription(ingredient.getIngredientDescription())
//                .ingredientPricePerWeightResponseDTOs(ingredientPricePerWeightResponseDTOs)
                .build();
    }
}
