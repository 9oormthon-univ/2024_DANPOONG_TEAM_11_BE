package shop.nongdam.nongdambackend.restaurant.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.nongdam.nongdambackend.global.domain.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseEntity {
    private String name;
    private int price;
    private String image;

    @Column(name = "is_main_menu")
    private boolean isMainMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @Builder
    public Menu(String name, int price, String image, boolean isMainMenu) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.isMainMenu = isMainMenu;
    }
}
