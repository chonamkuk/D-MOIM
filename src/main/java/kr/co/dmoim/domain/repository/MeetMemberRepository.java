package kr.co.dmoim.domain.repository;

import kr.co.dmoim.domain.entity.MeetMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MeetMemberRepository extends JpaRepository<MeetMemberEntity, Long>, JpaSpecificationExecutor<MeetMemberEntity> {
}
