package kr.co.dmoim.domain.repository;

import kr.co.dmoim.domain.entity.MeetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MeetRepository extends JpaRepository<MeetEntity, Long>, JpaSpecificationExecutor<MeetEntity> {
}
