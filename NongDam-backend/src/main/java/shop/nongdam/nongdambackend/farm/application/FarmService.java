package shop.nongdam.nongdambackend.farm.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.nongdam.nongdambackend.auth.exception.EmailNotFoundException;
import shop.nongdam.nongdambackend.farm.api.dto.request.FarmSaveRequestDTO;
import shop.nongdam.nongdambackend.farm.api.dto.response.FarmInfoResponseDTO;
import shop.nongdam.nongdambackend.farm.domain.Farm;
import shop.nongdam.nongdambackend.farm.domain.repository.FarmRepository;
import shop.nongdam.nongdambackend.member.domain.Member;
import shop.nongdam.nongdambackend.member.domain.Role;
import shop.nongdam.nongdambackend.member.domain.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FarmService {
    private final FarmRepository farmRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public FarmInfoResponseDTO saveFarmInfo(String email, FarmSaveRequestDTO farmSaveRequestDTO){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);

        Farm farm = buildNewFarm(member, farmSaveRequestDTO);
        farmRepository.save(farm);

        member.updateRole(Role.ROLE_PRODUCER);
        return FarmInfoResponseDTO.from(farm);
    }


    private Farm buildNewFarm(Member member, FarmSaveRequestDTO farmSaveRequestDTO){
        return Farm.builder()
                .member(member)
                .farmName(farmSaveRequestDTO.farmName())
                .profileImage(farmSaveRequestDTO.profileImage())
                .farmRepresentative(farmSaveRequestDTO.farmRepresentative())
                .phoneNumber(farmSaveRequestDTO.phoneNumber())
                .businessRegistrationNumber(farmSaveRequestDTO.businessRegistrationNumber())
                .address(farmSaveRequestDTO.address())
                .latitude(farmSaveRequestDTO.latitude())
                .longitude(farmSaveRequestDTO.longitude())
                .build();
    }
}
