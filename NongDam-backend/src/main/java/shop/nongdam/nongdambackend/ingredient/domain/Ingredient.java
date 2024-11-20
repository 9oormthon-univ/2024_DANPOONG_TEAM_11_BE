package shop.nongdam.nongdambackend.ingredient.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.nongdam.nongdambackend.farm.domain.Farm;
import shop.nongdam.nongdambackend.global.domain.BaseEntity;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    @Column(name = "ingredient_name", nullable = false)
    private String ingredientName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_ugly_reason_id", nullable = false)
    private IngredientUglyReason ingredientUglyReason;

    @Column(name = "ugly_description", nullable = false)
    private String uglyDescription;

    @Column(name = "ingredient_description", nullable = false)
    private String ingredientDescription;

    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_category_id", nullable = false)
    private IngredientCategory ingredientCategory;

    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngredientPricePerWeight> ingredientPricePerWeights;

    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngredientProductTag> ingredientProductTags;

    @Builder
    public Ingredient(Farm farm, String ingredientName, IngredientUglyReason ingredientUglyReason,
                      String uglyDescription, String ingredientDescription, String thumbnail,
                      IngredientCategory ingredientCategory, List<IngredientPricePerWeight> ingredientPricePerWeights,
                      List<IngredientProductTag> ingredientProductTags) {
        this.farm = farm;
        this.ingredientName = ingredientName;
        this.ingredientUglyReason = ingredientUglyReason;
        this.uglyDescription = uglyDescription;
        this.ingredientDescription = ingredientDescription;
        this.thumbnail = thumbnail;
        this.ingredientCategory = ingredientCategory;
        this.ingredientPricePerWeights = ingredientPricePerWeights;
        this.ingredientProductTags = ingredientProductTags;
    }

    public void addIngredientPricePerWeight(IngredientPricePerWeight pricePerWeight) {
        this.ingredientPricePerWeights.add(pricePerWeight);
        pricePerWeight.setIngredient(this);
    }

    public void removeIngredientPricePerWeight(IngredientPricePerWeight pricePerWeight) {
        this.ingredientPricePerWeights.remove(pricePerWeight);
        pricePerWeight.setIngredient(null);
    }

    public void addIngredientProductTag(IngredientProductTag productTag) {
        this.ingredientProductTags.add(productTag);
        productTag.setIngredient(this);
    }

    public void removeIngredientProductTag(IngredientProductTag productTag) {
        this.ingredientProductTags.remove(productTag);
        productTag.setIngredient(null);
    }
}
