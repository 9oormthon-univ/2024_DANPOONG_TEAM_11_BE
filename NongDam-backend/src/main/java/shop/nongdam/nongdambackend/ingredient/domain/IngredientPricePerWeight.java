package shop.nongdam.nongdambackend.ingredient.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IngredientPricePerWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Column(nullable = false)
    private Long weight;

    @Column(nullable = false)
    private Long price;

    @Builder
    public IngredientPricePerWeight(Long weight, Long price, Ingredient ingredient) {
        this.weight = weight;
        this.price = price;
        this.ingredient = ingredient;
    }
}
