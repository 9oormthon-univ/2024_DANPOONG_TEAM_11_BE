package shop.nongdam.nongdambackend.restaurant.domain.repository.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.nongdam.nongdambackend.restaurant.domain.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
