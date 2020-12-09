package kr.co.dmoim.domain.repository;

import kr.co.dmoim.domain.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByIdAccount(String idAccount);
}
