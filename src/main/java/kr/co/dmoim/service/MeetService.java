package kr.co.dmoim.service;

import kr.co.dmoim.domain.entity.AccountEntity;
import kr.co.dmoim.domain.entity.AsEntity;
import kr.co.dmoim.domain.entity.MeetEntity;
import kr.co.dmoim.domain.entity.MeetMemberEntity;
import kr.co.dmoim.domain.repository.AccountRepository;
import kr.co.dmoim.domain.repository.MeetMemberRepository;
import kr.co.dmoim.domain.repository.MeetRepository;
import kr.co.dmoim.dto.*;
import kr.co.dmoim.util.AES256Util;
import kr.co.dmoim.util.ModelMapperUtils;
import kr.co.dmoim.util.SearchSpec;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class MeetService {
    @Autowired
    AES256Util aes;

    private MeetRepository meetRepository;
    private AccountRepository accountRepository;
    private AccountService accountService;
    private MeetMemberRepository meetMemberRepository;

    @Transactional
    public Long save(MeetDto meetDto) {
        Long seqMeet = meetRepository.save(meetDto.toEntity()).getSeqMeet();

        /**
         * todo 맵핑관계 정리
         * 1. 방안A
         * meet에 있는 meetmember 리스트를 일대다, 연관관계의 주인이 아닌 것으로 변경 mappedBy = "meet"
         * meetmember 에 있는 meet를 다대일, 연관관계의 주인으로 설정
         * meetmember에서 setMeet 메소드에 아래와 같은 로직 추가
         *         if(this.team != null) {
         *             this.team.getMembers().remove(this); // update 할때
         *         }
         *
         *         this.team = team;
         *         team.getMembers().add(this); // insert 할때
         *
         * 2. 방안B
         * manytomany 사용
         * JoinTable로 meeting_member 를 지정
         * 계정이 없는 경우도 있으므로 meet - account 가 항상 맵핑되지 않는다 -> 방안B는 계정없는 참여자 구현이 어려움
         *
         * 3. 방안C
         * manytomany 새로운 기본키 사용 231 페이지
         */

        return seqMeet;
    }

    public Long save(MeetDto meetDto, List<String> idAccounts, AccountDto accountSession) throws Exception {

        // todo: 한번에 저장할 수 있도록 개선
        // 모임 생성
        MeetEntity meetEntity = meetRepository.save(meetDto.toEntity());

        // 반복 돌면서 참여자 정보 생성
        for(String idAccount : idAccounts) {
            AccountEntity accountEntity = accountRepository.findByIdAccount(idAccount).get();
            MeetMemberEntity meetMemberEntity = MeetMemberEntity.builder()
                    .accountEntity(accountEntity)
                    .meetEntity(meetEntity)
                    .build();

            meetMemberRepository.save(meetMemberEntity);
        }

        return meetEntity.getSeqMeet();
    }

    public MeetDto getDetail(Long seqMeet) throws Exception {
        MeetEntity meetEntity = meetRepository.getOne(seqMeet);
        MeetDto meetDto = ModelMapperUtils.getModelMapper().map(meetEntity, MeetDto.class);

        return meetDto;
    }

//    public HashMap getList(Pageable pageble, SearchDto searchDto) throws Exception {
//        HashMap result = new HashMap();
//        Page<MeetEntity> entityPage = null;
//        entityPage = meetRepository.findAll((Specification<MeetEntity>) SearchSpec)
//    }
}
