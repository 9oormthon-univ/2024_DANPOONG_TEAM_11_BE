package shop.nongdam.nongdambackend.farm.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.nongdam.nongdambackend.farm.api.dto.request.FarmRequestDTO;
import shop.nongdam.nongdambackend.farm.api.dto.response.FarmRegistrationResponseDTO;
import shop.nongdam.nongdambackend.farm.application.FarmService;
import shop.nongdam.nongdambackend.global.annotation.CurrentMemberEmail;
import shop.nongdam.nongdambackend.global.template.ApiResponseTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/farm")
public class FarmController implements FarmDocs{
    private final FarmService farmService;

    @Override
    @PostMapping
    public ApiResponseTemplate<FarmRegistrationResponseDTO> registrationFarm(
            @CurrentMemberEmail String email,
            @RequestBody FarmRequestDTO farmRequestDTO
    ){
        FarmRegistrationResponseDTO farmRegistrationResponseDTO = farmService.saveFarmInfo(email, farmRequestDTO);
        return ApiResponseTemplate.created("농산물 생산자 등록", farmRegistrationResponseDTO);
    }
}
