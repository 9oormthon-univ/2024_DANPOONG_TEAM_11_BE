package shop.nongdam.nongdambackend.farm.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.nongdam.nongdambackend.auth.exception.EmailNotFoundException;
import shop.nongdam.nongdambackend.farm.api.dto.request.FarmSaveRequestDTO;
import shop.nongdam.nongdambackend.farm.api.dto.response.FarmInfoResponseDTO;
import shop.nongdam.nongdambackend.farm.api.dto.response.FarmInfoResponseDTOs;
import shop.nongdam.nongdambackend.farm.domain.Farm;
import shop.nongdam.nongdambackend.farm.domain.repository.FarmRepository;
import shop.nongdam.nongdambackend.farm.exception.FarmAlreadyExistException;
import shop.nongdam.nongdambackend.farm.exception.FarmNotFoundException;
import shop.nongdam.nongdambackend.global.dto.PageInfoResDto;
import shop.nongdam.nongdambackend.member.domain.Member;
import shop.nongdam.nongdambackend.member.domain.Role;
import shop.nongdam.nongdambackend.member.domain.repository.MemberRepository;
import shop.nongdam.nongdambackend.region.domain.Region;
import shop.nongdam.nongdambackend.region.domain.repository.RegionRepository;
import shop.nongdam.nongdambackend.region.exception.RegionNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FarmService {
    private final FarmRepository farmRepository;
    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;

    @Transactional
    public FarmInfoResponseDTO saveFarmInfo(String email, FarmSaveRequestDTO farmSaveRequestDTO){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);

        Optional<Farm> farmOptional = farmRepository.findByMemberId(member.getId());
        if (farmOptional.isPresent()) {
            throw new FarmAlreadyExistException();
        }

        Farm farm = buildNewFarm(member, farmSaveRequestDTO);
        farmRepository.save(farm);

        member.updateRole(Role.ROLE_PRODUCER);
        return FarmInfoResponseDTO.from(farm);
    }

    public FarmInfoResponseDTO findById(Long farmId) {
        Farm farm = farmRepository.findById(farmId)
                .orElseThrow(FarmNotFoundException::new);

        return FarmInfoResponseDTO.from(farm);
    }

    public FarmInfoResponseDTOs findAll(Pageable pageable) {
        Page<Farm> farms = farmRepository.findAllFarms(pageable);

        List<FarmInfoResponseDTO> farmInfoResponseDTOs = farms.stream()
                .map(FarmInfoResponseDTO::from)
                .toList();

        return FarmInfoResponseDTOs.of(farmInfoResponseDTOs, PageInfoResDto.from(farms));
    }

    private Farm buildNewFarm(Member member, FarmSaveRequestDTO farmSaveRequestDTO){
        Region region = regionRepository.findByName(farmSaveRequestDTO.region())
                .orElseThrow(RegionNotFoundException::new);

        return Farm.builder()
                .member(member)
                .farmName(farmSaveRequestDTO.farmName())
                .profileImage(farmSaveRequestDTO.profileImage())
                .farmRepresentative(farmSaveRequestDTO.farmRepresentative())
                .phoneNumber(farmSaveRequestDTO.phoneNumber())
                .businessRegistrationNumber(farmSaveRequestDTO.businessRegistrationNumber())
                .address(farmSaveRequestDTO.address())
                .region(region)
                .latitude(farmSaveRequestDTO.latitude())
                .longitude(farmSaveRequestDTO.longitude())
                .build();
    }


}
