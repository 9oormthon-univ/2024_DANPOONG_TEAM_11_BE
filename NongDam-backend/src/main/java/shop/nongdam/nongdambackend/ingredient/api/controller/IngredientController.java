package shop.nongdam.nongdambackend.ingredient.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shop.nongdam.nongdambackend.global.annotation.CurrentMemberEmail;
import shop.nongdam.nongdambackend.global.template.ApiResponseTemplate;
import shop.nongdam.nongdambackend.ingredient.api.dto.request.IngredientSaveRequestDTO;
import shop.nongdam.nongdambackend.ingredient.api.dto.response.IngredientInfoResponseDTO;
import shop.nongdam.nongdambackend.ingredient.api.dto.response.IngredientInfoResponseDTOs;
import shop.nongdam.nongdambackend.ingredient.application.IngredientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ingredients")
public class IngredientController implements IngredientDocs{
    public final IngredientService ingredientService;

    @Override
    @PostMapping(
            value = "/{farmId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ApiResponseTemplate<IngredientInfoResponseDTO> save(
            @PathVariable Long farmId,
            @CurrentMemberEmail String email,
            @RequestPart IngredientSaveRequestDTO ingredientSaveRequestDto,
            @RequestPart(required = false) List<MultipartFile> ingredientImages
    ){
        IngredientInfoResponseDTO ingredientInfoResponseDTO = ingredientService
                .saveIngredientInfo(email, farmId, ingredientSaveRequestDto, ingredientImages);

        return ApiResponseTemplate.created("식료품 등록 성공", ingredientInfoResponseDTO);
    }

    @Override
    @GetMapping("{ingredientId}")
    public ApiResponseTemplate<IngredientInfoResponseDTO> findById(@PathVariable Long ingredientId){
        IngredientInfoResponseDTO ingredientInfoResponseDTO = ingredientService.findById(ingredientId);
        return ApiResponseTemplate.ok("식료품 상세 조회 성공", ingredientInfoResponseDTO);
    }

    @Override
    @GetMapping
    public ApiResponseTemplate<IngredientInfoResponseDTOs> findAll(
            @RequestParam(defaultValue = "전체", name = "category") String category,
            @RequestParam(defaultValue = "전체", name = "region") String region,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size
    ){
        IngredientInfoResponseDTOs ingredientInfoResponseDTOs = ingredientService.findAll(category, region, PageRequest.of(page, size));
        return ApiResponseTemplate.ok("식료품 전체 조회 성공", ingredientInfoResponseDTOs);
    }
}
