package shop.nongdam.nongdambackend.restaurant.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.nongdam.nongdambackend.global.domain.BaseEntity;
import shop.nongdam.nongdambackend.member.domain.Member;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends BaseEntity {

    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

    @Column(name = "restaurant_representative", nullable = false)
    private String restaurantRepresentative;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "business_registration_number", nullable = false)
    private String businessRegistrationNumber;

    @Column(name = "address", nullable = false)
    private String address;

    private double latitude;

    private double longitude;

    @Column(name = "represent_image")
    private String representImage;

    @Column(name = "open_time")
    private Long openTime;

    @Column(name = "close_time")
    private Long closeTime;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menu;

    @Builder
    public Restaurant(String restaurantName, String restaurantRepresentative, Member member, String phoneNumber,
                      String businessRegistrationNumber, String address, double latitude, double longitude,
                      String representImage, Long openTime, Long closeTime, List<Menu> menu) {
        this.restaurantName = restaurantName;
        this.restaurantRepresentative = restaurantRepresentative;
        this.member = member;
        this.phoneNumber = phoneNumber;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.representImage = representImage;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.menu = menu;
    }
}
