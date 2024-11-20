package shop.nongdam.nongdambackend.ingredient.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IngredientProductTag {
    @EmbeddedId
    private IngredientProductTagId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productTagId")
    @JoinColumn(name = "product_tag_id", nullable = false)
    private ProductTag productTag;

    @Builder
    public IngredientProductTag(Ingredient ingredient, ProductTag productTag) {
        this.id = new IngredientProductTagId(ingredient.getId(), productTag.getId());
        this.ingredient = ingredient;
        this.productTag = productTag;
    }

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
