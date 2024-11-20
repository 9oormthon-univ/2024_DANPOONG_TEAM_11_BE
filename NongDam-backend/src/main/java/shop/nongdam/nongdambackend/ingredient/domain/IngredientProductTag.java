package shop.nongdam.nongdambackend.ingredient.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IngredientProductTag {
    @EmbeddedId
    private IngredientProductTagId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productTagId")
    @JoinColumn(name = "product_tag_id", nullable = false)
    private ProductTag productTag;

    public void setIngredient(Ingredient ingredient) {
        if (this.ingredient != null) {
            this.ingredient.getIngredientProductTags().remove(this);
        }
        this.ingredient = ingredient;
        if (ingredient != null && !ingredient.getIngredientProductTags().contains(this)) {
            ingredient.getIngredientProductTags().add(this);
        }
    }
}

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
class IngredientProductTagId {

    private Long ingredientId;
    private Long productTagId;

    public IngredientProductTagId(Long ingredientId, Long productTagId) {
        this.ingredientId = ingredientId;
        this.productTagId = productTagId;
    }
}
