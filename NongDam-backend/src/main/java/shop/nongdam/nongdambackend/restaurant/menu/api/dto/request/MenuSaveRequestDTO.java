package shop.nongdam.nongdambackend.restaurant.menu.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MenuSaveRequestDTO(
        @NotNull(message = "식당 id는 필수 입력값입니다.")
        Long restaurantId,

        @NotBlank(message = "메뉴 이름은 필수 입력값입니다.")
        String name,

        @Min(value = 1, message = "메뉴 가격은 1 이상이어야 합니다.")
        int price,

        @NotBlank(message = "농산물 이름은 필수 입력값입니다.")
        String farmProduce,

        @NotBlank(message = "메뉴 메인 설명은 필수 입력값입니다.")
        String mainDescription,

        @NotBlank(message = "메뉴 보조 설명은 필수 입력값입니다.")
        String subDescription,

        boolean isMainMenu
) {
}
