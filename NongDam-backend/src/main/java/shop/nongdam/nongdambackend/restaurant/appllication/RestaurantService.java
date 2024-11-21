package shop.nongdam.nongdambackend.restaurant.appllication;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import shop.nongdam.nongdambackend.global.aws.application.ImageService;
import shop.nongdam.nongdambackend.global.dto.PageInfoResDto;
import shop.nongdam.nongdambackend.member.domain.Member;
import shop.nongdam.nongdambackend.member.domain.Role;
import shop.nongdam.nongdambackend.member.domain.repository.MemberRepository;
import shop.nongdam.nongdambackend.member.exception.MemberNotFoundException;
import shop.nongdam.nongdambackend.restaurant.api.dto.request.RestaurantDetailSaveRequestDTO;
import shop.nongdam.nongdambackend.restaurant.api.dto.request.RestaurantSaveRequestDTO;
import shop.nongdam.nongdambackend.restaurant.api.dto.response.RestaurantDetailInfoResponseDTO;
import shop.nongdam.nongdambackend.restaurant.api.dto.response.RestaurantInfoResponseDTO;
import shop.nongdam.nongdambackend.restaurant.api.dto.response.RestaurantInfoResponseDTOs;
import shop.nongdam.nongdambackend.restaurant.domain.Restaurant;
import shop.nongdam.nongdambackend.restaurant.domain.repository.RestaurantRepository;
import shop.nongdam.nongdambackend.restaurant.exception.AccessDeniedRestaurantException;
import shop.nongdam.nongdambackend.restaurant.exception.RestaurantNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;
    private final ImageService imageService;

    @Transactional
    public RestaurantInfoResponseDTO save(String memberEmail, RestaurantSaveRequestDTO restaurantSaveRequestDTO) {
        Member member = memberRepository.findByEmail(memberEmail)
                .orElseThrow(MemberNotFoundException::new);

        Restaurant restaurant = buildNewRestaurant(restaurantSaveRequestDTO, member);
        restaurantRepository.save(restaurant);

        member.updateRole(Role.ROLE_RESTAURANT);
        return RestaurantInfoResponseDTO.from(restaurant);
    }

    @Transactional
    public RestaurantDetailInfoResponseDTO registerRestaurantDetail(
            String email,
            RestaurantDetailSaveRequestDTO restaurantDetailSaveRequestDTO,
            MultipartFile restaurantImage) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);
        Restaurant restaurant = restaurantRepository.findById(restaurantDetailSaveRequestDTO.restaurantId())
                .orElseThrow(RestaurantNotFoundException::new);

        if (!member.equals(restaurant.getMember())) {
            throw new AccessDeniedRestaurantException();
        }

        restaurant.updateDetail(
                restaurantDetailSaveRequestDTO.latitude(),
                restaurantDetailSaveRequestDTO.longitude(),
                imageService.saveImage(restaurantImage),
                restaurantDetailSaveRequestDTO.openTime(),
                restaurantDetailSaveRequestDTO.closeTime());

        return RestaurantDetailInfoResponseDTO.from(restaurant);
    }

    public RestaurantInfoResponseDTO findById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(RestaurantNotFoundException::new);

        return RestaurantInfoResponseDTO.from(restaurant);
    }

    public RestaurantInfoResponseDTOs findAll(Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findAllRestaurants(pageable);

        List<RestaurantInfoResponseDTO> restaurantInfoResponseDTOs = restaurants.stream()
                .map(RestaurantInfoResponseDTO::from)
                .toList();

        return RestaurantInfoResponseDTOs.of(restaurantInfoResponseDTOs, PageInfoResDto.from(restaurants));
    }

    @Transactional
    public void deleteById(String email, Long restaurantId) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(RestaurantNotFoundException::new);

        if (!restaurant.isOwner(member)) {
            throw new AccessDeniedRestaurantException();
        }
        restaurant.delete();
    }

    private Restaurant buildNewRestaurant(RestaurantSaveRequestDTO restaurantSaveRequestDTO, Member member) {
        return Restaurant.builder()
                .restaurantName(restaurantSaveRequestDTO.restaurantName())
                .restaurantRepresentative(restaurantSaveRequestDTO.restaurantRepresentative())
                .member(member)
                .phoneNumber(restaurantSaveRequestDTO.phoneNumber())
                .businessRegistrationNumber(restaurantSaveRequestDTO.businessRegistrationNumber())
                .address(restaurantSaveRequestDTO.address())
                .build();
    }
}
