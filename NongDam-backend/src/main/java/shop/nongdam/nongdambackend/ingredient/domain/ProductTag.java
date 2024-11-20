package shop.nongdam.nongdambackend.ingredient.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "productTag", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngredientProductTag> ingredientProductTags;

    @Builder
    public ProductTag(String name) {
        this.name = name;
    }
}
