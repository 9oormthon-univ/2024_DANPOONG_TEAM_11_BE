package shop.nongdam.nongdambackend.restaurant.menu.api.dto.response;

import lombok.Builder;
import shop.nongdam.nongdambackend.restaurant.menu.domain.Menu;

@Builder
public record MenuInfoResponseDTO(
        Long id,
        String name,
        int price,
        String farmProduce,
        String image,
        String mainDescription,
        String subDescription,
        boolean isMainMenu
) {
    public static MenuInfoResponseDTO from(Menu menu) {
        return MenuInfoResponseDTO.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .image(menu.getImage())
                .mainDescription(menu.getMainDescription())
                .subDescription(menu.getSubDescription())
                .isMainMenu(menu.isMainMenu())
                .build();
    }
}
