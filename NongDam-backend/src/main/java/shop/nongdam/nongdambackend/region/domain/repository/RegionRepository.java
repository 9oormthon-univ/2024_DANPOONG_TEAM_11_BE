package shop.nongdam.nongdambackend.region.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.nongdam.nongdambackend.region.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
