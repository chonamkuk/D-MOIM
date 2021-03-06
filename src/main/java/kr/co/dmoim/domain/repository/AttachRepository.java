package kr.co.dmoim.domain.repository;

import kr.co.dmoim.domain.entity.AttachEntity;
import kr.co.dmoim.domain.entity.AttachId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachRepository extends JpaRepository<AttachEntity, AttachId> {
    List<AttachEntity> findByIdAttach(String idAttach);

    List<AttachEntity> findByIdAttachAndYnDel(String idAttach, String ynDel);

    AttachEntity findByIdAttachAndSnFileAttach(String idAttach, int snFileAttach);

    AttachEntity findTopByIdAttachOrderBySnFileAttachDesc(String idAttach);
}
