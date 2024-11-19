package shop.nongdam.nongdambackend.restaurant.appllication;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.nongdam.nongdambackend.global.dto.PageInfoResDto;
import shop.nongdam.nongdambackend.restaurant.api.dto.request.RestaurantSaveRequestDTO;
import shop.nongdam.nongdambackend.restaurant.api.dto.response.RestaurantInfoResponseDTO;
import shop.nongdam.nongdambackend.restaurant.api.dto.response.RestaurantInfoResponseDTOs;
import shop.nongdam.nongdambackend.restaurant.domain.Restaurant;
import shop.nongdam.nongdambackend.restaurant.domain.repository.menu.MenuRepository;
import shop.nongdam.nongdambackend.restaurant.domain.repository.RestaurantRepository;
import shop.nongdam.nongdambackend.restaurant.exception.RestaurantNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public RestaurantInfoResponseDTO save(RestaurantSaveRequestDTO restaurantSaveRequestDTO) {
        Restaurant restaurant = buildNewRestaurant(restaurantSaveRequestDTO);
        restaurantRepository.save(restaurant);

        return RestaurantInfoResponseDTO.from(restaurant);
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

    public void deleteById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(RestaurantNotFoundException::new);

        restaurantRepository.deleteById(restaurantId);
    }

    private Restaurant buildNewRestaurant(RestaurantSaveRequestDTO restaurantSaveRequestDTO) {
        return Restaurant.builder()
                .restaurantName(restaurantSaveRequestDTO.restaurantName())
                .restaurantRepresentative(restaurantSaveRequestDTO.restaurantRepresentative())
                .phoneNumber(restaurantSaveRequestDTO.phoneNumber())
                .businessRegistrationNumber(restaurantSaveRequestDTO.businessRegistrationNumber())
                .address(restaurantSaveRequestDTO.address())
                .build();
    }
}
