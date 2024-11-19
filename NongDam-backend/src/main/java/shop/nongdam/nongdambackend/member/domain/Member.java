package shop.nongdam.nongdambackend.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.nongdam.nongdambackend.global.domain.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

    private String email;

    private String name;

    private String picture;

    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private boolean firstLogin;

    private String certificate;

    @Builder
    public Member(String email, String name, String picture, SocialType socialType, Role role, boolean firstLogin,
                  String certificate) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.socialType = socialType;
        this.role = role;
        this.firstLogin = firstLogin;
        this.certificate = certificate;
    }
}
