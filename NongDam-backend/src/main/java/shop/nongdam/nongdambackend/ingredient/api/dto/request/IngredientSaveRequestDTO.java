package shop.nongdam.nongdambackend.ingredient.api.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record IngredientSaveRequestDTO(
        @NotBlank(message = "식표품 사진(링크)는 필수 입력값입니다.")
        String thumbnail,
        @NotBlank(message = "식료품 이름은 필수 입력값입니다.")
        String ingredientName,
        @NotBlank(message = "식료품 카테고리는 필수 입력값입니다.")
        String ingredientCategory,
        @NotBlank(message = "못난이인 이유는 필수 입력값입니다.")
        String uglyReason,
        @NotBlank(message = "못난이 이유 설명은 필수 입력값입니다.")
        String uglyDescription,
        List<String> productTag,
        @NotBlank(message = "식료품 상세 설명은 필수 입력값입니다.")
        String ingredientDescription,
        List<IngredientPricePerWeightRequestDTO> ingredientPricePerWeightRequestDTOs
) {
}
