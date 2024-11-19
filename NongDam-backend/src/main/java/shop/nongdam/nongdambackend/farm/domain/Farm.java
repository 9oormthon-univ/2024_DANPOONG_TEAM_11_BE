package shop.nongdam.nongdambackend.farm.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.nongdam.nongdambackend.global.domain.BaseEntity;
import shop.nongdam.nongdambackend.member.domain.Member;

@Entity
@Getter
@NoArgsConstructor
public class Farm extends BaseEntity  {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_user_id", nullable = false)
    private Member member;

    @Column(name = "farm_name", nullable = false)
    private String farmName;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "farm_representative")
    private String farmRepresentative;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "business_registration_number")
    private Long businessRegistrationNumber;

    @Column(name = "address")
    private String address;

//    @Column(name = "region_id")
//    private Long regionId;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Builder
    public Farm(Member member, String farmName, String profileImage,
                String farmRepresentative, String phoneNumber,
                Long businessRegistrationNumber, String address,
                Double latitude, Double longitude) {
        this.member = member;
        this.farmName = farmName;
        this.profileImage = profileImage;
        this.farmRepresentative = farmRepresentative;
        this.phoneNumber = phoneNumber;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
