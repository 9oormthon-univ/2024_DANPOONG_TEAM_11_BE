package shop.nongdam.nongdambackend.farm.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import shop.nongdam.nongdambackend.farm.api.dto.request.FarmRequestDTO;
import shop.nongdam.nongdambackend.farm.api.dto.response.FarmRegistrationResponseDTO;
import shop.nongdam.nongdambackend.global.annotation.CurrentMemberEmail;
import shop.nongdam.nongdambackend.global.template.ApiResponseTemplate;

@Tag(name = "[판매자 API]", description = "농산물 판매자(농가) 관련 API")
public interface FarmDocs {
    @Operation(summary = "농산물 판매자 등록", description = "카카오 소셜 회원가입 후 농산물 판매자를 등록합니다.",
        responses = {
                @ApiResponse(responseCode = "201", description = "농산물 판매자 등록 성공",
                    content = @Content(schema = @Schema(implementation = FarmRegistrationResponseDTO.class))),
                @ApiResponse(responseCode = "401", description = "인증 실패"),
                @ApiResponse(responseCode = "404", description = "사용자 찾을 수 없음"),
                @ApiResponse(responseCode = "500", description = "서버 오류")
        }
    )
    ApiResponseTemplate<FarmRegistrationResponseDTO> registrationFarm(
            @Parameter(description = "사용자 이메일", required = true) @CurrentMemberEmail String email,
            @Parameter(description = "농산물 판매자 정보", required = true) @RequestBody FarmRequestDTO farmRequestDTO
    );
}
