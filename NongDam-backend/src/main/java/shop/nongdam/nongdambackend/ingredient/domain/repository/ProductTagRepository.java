package shop.nongdam.nongdambackend.ingredient.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.nongdam.nongdambackend.ingredient.domain.ProductTag;

import java.util.Optional;

public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {
    Optional<ProductTag> findByName(String productTagName);
}
