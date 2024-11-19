package shop.nongdam.nongdambackend.farm.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.nongdam.nongdambackend.farm.domain.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}
