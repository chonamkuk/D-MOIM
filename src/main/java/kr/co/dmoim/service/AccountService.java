package kr.co.dmoim.service;

import kr.co.dmoim.domain.entity.AccountEntity;
import kr.co.dmoim.domain.repository.AccountRepository;
import kr.co.dmoim.dto.AccountDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccountService implements UserDetailsService {
    private AccountRepository accountRepository;

    public Long saveAccount(AccountDto accountDto) {
        return accountRepository.save(accountDto.toEntity()).getSeqAccount();
    }

    @Override
    public UserDetails loadUserByUsername(String idAccount) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountRepository.findByIdAccount(idAccount).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        AccountDto accountDto = AccountDto.builder()
                .idAccount(accountEntity.getIdAccount())
                .passwordAccount(accountEntity.getPasswordAccount())
                .nameAccount(accountEntity.getNameAccount())
                .emailAccount(accountEntity.getEmailAccount())
                .roleAccount(accountEntity.getRoleAccount())
                .build();

        return accountDto;
    }
}
