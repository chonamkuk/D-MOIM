package kr.co.dmoim.test.domain.repository;

import kr.co.dmoim.test.domain.entity.CrudEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudRepository extends JpaRepository<CrudEntity, Long> {
}
