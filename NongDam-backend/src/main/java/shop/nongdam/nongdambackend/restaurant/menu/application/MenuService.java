package shop.nongdam.nongdambackend.restaurant.menu.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import shop.nongdam.nongdambackend.global.aws.application.ImageService;
import shop.nongdam.nongdambackend.member.domain.Member;
import shop.nongdam.nongdambackend.member.domain.repository.MemberRepository;
import shop.nongdam.nongdambackend.member.exception.MemberNotFoundException;
import shop.nongdam.nongdambackend.restaurant.domain.Restaurant;
import shop.nongdam.nongdambackend.restaurant.domain.repository.RestaurantRepository;
import shop.nongdam.nongdambackend.restaurant.exception.AccessDeniedRestaurantException;
import shop.nongdam.nongdambackend.restaurant.exception.RestaurantNotFoundException;
import shop.nongdam.nongdambackend.restaurant.menu.domain.Menu;
import shop.nongdam.nongdambackend.restaurant.menu.api.dto.request.MenuSaveRequestDTO;
import shop.nongdam.nongdambackend.restaurant.menu.api.dto.response.MenuInfoResponseDTO;
import shop.nongdam.nongdambackend.restaurant.menu.domain.repository.MenuRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {
    private final MenuRepository menuRepository;
    private final ImageService imageService;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MenuInfoResponseDTO save(String email,MenuSaveRequestDTO menuSaveRequestDTO, MultipartFile menuImage) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        Restaurant restaurant = restaurantRepository.findById(menuSaveRequestDTO.restaurantId())
                .orElseThrow(RestaurantNotFoundException::new);

        if (!member.equals(restaurant.getMember())) {
            throw new AccessDeniedRestaurantException();
        }

        String menuImageUrl = imageService.saveImage(menuImage);
        Menu menu = Menu.builder()
                .name(menuSaveRequestDTO.name())
                .price(menuSaveRequestDTO.price())
                .image(menuImageUrl)
                .mainDescription(menuSaveRequestDTO.mainDescription())
                .subDescription(menuSaveRequestDTO.subDescription())
                .isMainMenu(menuSaveRequestDTO.isMainMenu())
                .restaurant(restaurant)
                .build();
        menuRepository.save(menu);

        return MenuInfoResponseDTO.from(menu);
    }
}
