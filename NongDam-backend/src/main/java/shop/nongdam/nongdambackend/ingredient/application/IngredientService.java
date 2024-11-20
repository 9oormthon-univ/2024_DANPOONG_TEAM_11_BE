package shop.nongdam.nongdambackend.ingredient.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.nongdam.nongdambackend.auth.exception.EmailNotFoundException;
import shop.nongdam.nongdambackend.farm.domain.Farm;
import shop.nongdam.nongdambackend.farm.domain.repository.FarmRepository;
import shop.nongdam.nongdambackend.farm.exception.FarmAccessDeniedException;
import shop.nongdam.nongdambackend.farm.exception.FarmNotFoundException;
import shop.nongdam.nongdambackend.ingredient.api.dto.request.IngredientPricePerWeightRequestDTOs;
import shop.nongdam.nongdambackend.ingredient.api.dto.request.IngredientSaveRequestDTO;
import shop.nongdam.nongdambackend.ingredient.api.dto.response.IngredientInfoResponseDTO;
import shop.nongdam.nongdambackend.ingredient.domain.*;
import shop.nongdam.nongdambackend.ingredient.domain.repository.IngredientCategoryRepository;
import shop.nongdam.nongdambackend.ingredient.domain.repository.IngredientRepository;
import shop.nongdam.nongdambackend.ingredient.domain.repository.IngredientUglyReasonRepository;
import shop.nongdam.nongdambackend.ingredient.exception.IngredientCategoryNotFoundException;
import shop.nongdam.nongdambackend.ingredient.exception.IngredientUglyReasonNotFoundException;
import shop.nongdam.nongdambackend.member.domain.Member;
import shop.nongdam.nongdambackend.member.domain.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final MemberRepository memberRepository;
    private final FarmRepository farmRepository;
    private final IngredientUglyReasonRepository ingredientUglyReasonRepository;
    private final IngredientCategoryRepository ingredientCategoryRepository;

    @Transactional
    public IngredientInfoResponseDTO saveIngredientInfo(
            String email,
            Long farmId,
            IngredientSaveRequestDTO ingredientSaveRequestDto
    ) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);

        Farm farm = farmRepository.findByMemberId(member.getId())
                .orElseThrow(FarmNotFoundException::new);

        if(!farm.getId().equals(farmId)) throw new FarmAccessDeniedException();

        Ingredient ingredient = buildNewIngredient(farm, ingredientSaveRequestDto);
        ingredientRepository.save(ingredient);

        return IngredientInfoResponseDTO.from(farm, ingredient);
    }

    private Ingredient buildNewIngredient(Farm farm, IngredientSaveRequestDTO ingredientSaveRequestDto) {
        IngredientUglyReason ingredientUglyReason = ingredientUglyReasonRepository
                .findByName(ingredientSaveRequestDto.uglyReason())
                .orElseThrow(IngredientUglyReasonNotFoundException::new);

        IngredientCategory ingredientCategory = ingredientCategoryRepository
                .findByName(ingredientSaveRequestDto.ingredientCategory())
                .orElseThrow(IngredientCategoryNotFoundException::new);

//        List<IngredientPricePerWeight> ingredientPricePerWeights = IngredientPricePerWeightRequestDTOs
//                .mapToIngredientPricePerWeights(ingredientSaveRequestDto.ingredientPricePerWeightRequestDTOs());

        // todo product tag
        // todo product prices
        return Ingredient.builder()
                .farm(farm)
                .ingredientName(ingredientSaveRequestDto.ingredientName())
                .ingredientUglyReason(ingredientUglyReason)
                .uglyDescription(ingredientSaveRequestDto.uglyDescription())
                .ingredientDescription(ingredientSaveRequestDto.ingredientDescription())
                .thumbnail(ingredientSaveRequestDto.thumbnail())
                .ingredientCategory(ingredientCategory)
                .build();
    }
}
