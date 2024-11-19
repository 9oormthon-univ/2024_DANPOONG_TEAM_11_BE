package shop.nongdam.nongdambackend.farm.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.nongdam.nongdambackend.auth.application.AuthMemberService;
import shop.nongdam.nongdambackend.auth.exception.EmailNotFoundException;
import shop.nongdam.nongdambackend.farm.api.dto.request.FarmRequestDTO;
import shop.nongdam.nongdambackend.farm.api.dto.response.FarmRegistrationResponseDTO;
import shop.nongdam.nongdambackend.farm.domain.Farm;
import shop.nongdam.nongdambackend.farm.domain.repository.FarmRepository;
import shop.nongdam.nongdambackend.member.domain.Member;
import shop.nongdam.nongdambackend.member.domain.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FarmService {
    private final FarmRepository farmRepository;
    private final MemberRepository memberRepository;
    private final AuthMemberService authMemberService;

    @Transactional
    public FarmRegistrationResponseDTO saveFarmInfo(String email, FarmRequestDTO farmRequestDTO){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);
        Farm farm = createFarm(member, farmRequestDTO);
        return FarmRegistrationResponseDTO.from(farm);
    }


    private Farm createFarm(Member member, FarmRequestDTO farmRequestDTO){
        return farmRepository.save(buildNewFarm(member, farmRequestDTO));
    }

    private Farm buildNewFarm(Member member, FarmRequestDTO farmRequestDTO){
        return Farm.builder()
                .member(member)
                .farmName(farmRequestDTO.farmName())
                .profileImage(farmRequestDTO.profileImage())
                .farmRepresentative(farmRequestDTO.farmRepresentative())
                .phoneNumber(farmRequestDTO.phoneNumber())
                .businessRegistrationNumber(farmRequestDTO.businessRegistrationNumber())
                .address(farmRequestDTO.address())
                .latitude(farmRequestDTO.latitude())
                .longitude(farmRequestDTO.longitude())
                .build();
    }
}
