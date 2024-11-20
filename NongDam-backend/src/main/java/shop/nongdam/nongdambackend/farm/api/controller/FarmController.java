package shop.nongdam.nongdambackend.farm.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import shop.nongdam.nongdambackend.farm.api.dto.request.FarmSaveRequestDTO;
import shop.nongdam.nongdambackend.farm.api.dto.response.FarmInfoResponseDTO;
import shop.nongdam.nongdambackend.farm.api.dto.response.FarmInfoResponseDTOs;
import shop.nongdam.nongdambackend.farm.application.FarmService;
import shop.nongdam.nongdambackend.global.annotation.CurrentMemberEmail;
import shop.nongdam.nongdambackend.global.template.ApiResponseTemplate;

// todo region <- address에서 매칭되는 주소 저장되게

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/farms")
public class FarmController implements FarmDocs{
    private final FarmService farmService;

    @Override
    @PostMapping
    public ApiResponseTemplate<FarmInfoResponseDTO> save(
            @CurrentMemberEmail String email,
            @RequestBody FarmSaveRequestDTO farmSaveRequestDTO
    ){
        FarmInfoResponseDTO farmInfoResponseDTO = farmService.saveFarmInfo(email, farmSaveRequestDTO);
        return ApiResponseTemplate.created("농산물 생산자 등록 성공", farmInfoResponseDTO);
    }

    @Override
    @GetMapping("{farmId}")
    public ApiResponseTemplate<FarmInfoResponseDTO> findById(@PathVariable Long farmId){
        FarmInfoResponseDTO farmInfoResponseDTO = farmService.findById(farmId);
        return ApiResponseTemplate.ok("농산물 생산자 상세 조회 성공", farmInfoResponseDTO);
    }

    @Override
    @GetMapping
    public ApiResponseTemplate<FarmInfoResponseDTOs> findAll(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size
    ){
        FarmInfoResponseDTOs farmInfoResponseDTOs = farmService.findAll(PageRequest.of(page, size));
        return ApiResponseTemplate.ok("농가 전체 조회 성공", farmInfoResponseDTOs);
    }
}
