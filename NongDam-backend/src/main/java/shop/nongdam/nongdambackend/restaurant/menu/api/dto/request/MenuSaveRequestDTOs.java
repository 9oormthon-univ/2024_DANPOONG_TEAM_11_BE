package shop.nongdam.nongdambackend.restaurant.menu.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record MenuSaveRequestDTOs(
        @JsonProperty("menuSaveRequestDTOs") List<MenuSaveRequestDTO> menuSaveRequestDTOs
) {
}