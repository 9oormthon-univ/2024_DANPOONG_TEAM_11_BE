package shop.nongdam.nongdambackend.auth.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import shop.nongdam.nongdambackend.auth.api.dto.request.RefreshTokenRequestDTO;
import shop.nongdam.nongdambackend.auth.api.dto.request.TokenRequestDTO;
import shop.nongdam.nongdambackend.auth.api.dto.response.IdTokenResponseDTO;
import shop.nongdam.nongdambackend.global.jwt.api.dto.TokenDTO;
import shop.nongdam.nongdambackend.global.template.ApiResponseTemplate;

@Tag(name = "[인증 API]", description = "인증 관련 API")
public interface AuthDocs {

    @Operation(summary = "OAuth2 인증 콜백", description = "OAuth2 인증을 통해 제공자로부터 ID 토큰을 반환받습니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "ID 토큰 반환 성공",
                            content = @Content(schema = @Schema(implementation = IdTokenResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    IdTokenResponseDTO callback(
            @Parameter(description = "OAuth2 제공자 (예: kakao, google)", required = true) String provider,
            @Parameter(description = "OAuth2 인증 코드", required = true) String code);

    @Operation(summary = "액세스 및 리프레시 토큰 발급", description = "회원 정보로부터 액세스 및 리프레시 토큰을 발급합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "토큰 발급 성공",
                            content = @Content(schema = @Schema(implementation = TokenDTO.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    ApiResponseTemplate<TokenDTO> generateAccessAndRefreshToken(
            @Parameter(description = "OAuth2 제공자 (예: kakao, google)", required = true) String provider,
            @Parameter(description = "토큰 요청 데이터", required = true) TokenRequestDTO tokenRequestDTO);

    @Operation(summary = "액세스 토큰 재발급", description = "리프레시 토큰을 이용해 새로운 액세스 토큰을 발급합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "액세스 토큰 발급 성공",
                            content = @Content(schema = @Schema(implementation = TokenDTO.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    ApiResponseTemplate<TokenDTO> generateAccessToken(
            @Parameter(description = "리프레시 토큰 요청 데이터", required = true) RefreshTokenRequestDTO refreshTokenRequestDTO);
}
