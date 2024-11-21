package shop.nongdam.nongdambackend.restaurant.menu.api.dto.request;

public record MenuSaveRequestDTO(
        Long restaurantId,
        String name,
        int price,
        String mainDescription,
        String subDescription,
        boolean isMainMenu
) {
}
