package shop.nongdam.nongdambackend.restaurant.menu.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MenuSaveRequestDTO(

        @NotBlank(message = "식당 ID는 필수 입력값입니다.")
        Long restaurantId,

        @NotBlank(message = "메뉴 이름은 필수 입력값입니다.")
        String name,

        @NotBlank(message = "메뉴 가격은 필수 입력값입니다.")
        int price,

        @NotBlank(message = "메뉴 메인 설명은 필수 입력값입니다.")
        String mainDescription,

        @NotBlank(message = "메뉴 보조 설명은 필수 입력값입니다.")
        String subDescription,

        @NotBlank(message = "메인 베뉴 여부 필수 입력값입니다.")
        boolean isMainMenu
) {
}
