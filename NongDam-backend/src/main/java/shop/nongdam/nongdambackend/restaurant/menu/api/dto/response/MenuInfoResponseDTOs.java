package shop.nongdam.nongdambackend.restaurant.menu.api.dto.response;

import java.util.List;
import java.util.Objects;
import lombok.Builder;
import shop.nongdam.nongdambackend.restaurant.menu.domain.Menu;

@Builder
public record MenuInfoResponseDTOs(
        List<MenuInfoResponseDTO> menuInfoResponseDTOS
) {
    public static MenuInfoResponseDTOs from(List<Menu> menu) {
        return new MenuInfoResponseDTOs(
                menu.stream()
                        .filter(Objects::nonNull)
                        .map(MenuInfoResponseDTO::from)
                        .toList()

        );
    }
}
