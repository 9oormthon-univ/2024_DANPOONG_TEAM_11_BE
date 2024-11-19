package shop.nongdam.nongdambackend.farm.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.nongdam.nongdambackend.global.domain.BaseEntity;
import shop.nongdam.nongdambackend.member.domain.Member;
import shop.nongdam.nongdambackend.region.domain.Region;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Farm extends BaseEntity  {

    @Column(name = "farm_name", nullable = false)
    private String farmName;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "farm_representative", nullable = false)
    private String farmRepresentative;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "business_registration_number", nullable = false)
    private Long businessRegistrationNumber;

    @Column(nullable = false)
    private String address;

    private Double latitude;

    private Double longitude;

    @OneToOne
    @JoinColumn(name = "farm_user_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Builder
    public Farm(Member member, String farmName, String profileImage,
                String farmRepresentative, String phoneNumber,
                Long businessRegistrationNumber, String address,
                Region region, Double latitude, Double longitude) {
        this.member = member;
        this.farmName = farmName;
        this.profileImage = profileImage;
        this.farmRepresentative = farmRepresentative;
        this.phoneNumber = phoneNumber;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.address = address;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
