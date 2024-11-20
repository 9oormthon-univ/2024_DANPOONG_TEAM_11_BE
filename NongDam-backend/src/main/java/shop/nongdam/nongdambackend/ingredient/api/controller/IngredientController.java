package shop.nongdam.nongdambackend.ingredient.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.nongdam.nongdambackend.global.annotation.CurrentMemberEmail;
import shop.nongdam.nongdambackend.global.template.ApiResponseTemplate;
import shop.nongdam.nongdambackend.ingredient.api.dto.request.IngredientSaveRequestDTO;
import shop.nongdam.nongdambackend.ingredient.api.dto.response.IngredientInfoResponseDTO;
import shop.nongdam.nongdambackend.ingredient.application.IngredientService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ingredient")
public class IngredientController {
    public final IngredientService ingredientService;

    @PostMapping("{farmId}")
    public ApiResponseTemplate<IngredientInfoResponseDTO> save(
            @PathVariable Long farmId,
            @CurrentMemberEmail String email,
            @RequestBody IngredientSaveRequestDTO ingredientSaveRequestDto
    ){
        IngredientInfoResponseDTO ingredientInfoResponseDTO = ingredientService
                .saveIngredientInfo(email, farmId, ingredientSaveRequestDto);

        return ApiResponseTemplate.created("식료품 등록 성공", ingredientInfoResponseDTO);
    }
}
