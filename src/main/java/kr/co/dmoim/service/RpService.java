package kr.co.dmoim.service;

import kr.co.dmoim.domain.entity.RpEntity;
import kr.co.dmoim.domain.repository.RpRepository;
import kr.co.dmoim.dto.RpDto;
import lombok.AllArgsConstructor;
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
public class RpService {
    private RpRepository rpRepository;


    public List<RpDto> getRpList(Long idRp) {
        List<RpEntity> rpEntities = rpRepository.findByIdRpAndYnDelRpOrderBySeqRpDesc(idRp,"N");

        List<RpDto> rpDtoList = new ArrayList<>();

        for(RpEntity rpEntity : rpEntities) {
            RpDto rpDto = RpDto.builder()
                    .idRp(rpEntity.getIdRp())
                    .seqRp(rpEntity.getSeqRp())
                    .commentRp(rpEntity.getCommentRp())
                    .regDtRp(rpEntity.getRegDtRp())
                    .userRp(rpEntity.getUserRp())
                    .ynDelRp(rpEntity.getYnDelRp())
                    .build();

            rpDtoList.add(rpDto);
        }

        return rpDtoList;
    }

    public Long saveAs(RpDto rpDto) {
        Long SeqRp = rpRepository.save(rpDto.toEntity()).getSeqRp();
        return SeqRp;
    }

    public Long updateRp(RpDto rpDto) {
        Long idRp = rpRepository.save(rpDto.toEntity()).getIdRp();
        return idRp;
    }

    @Transactional
    public void updateYnDelRp(Long seqRp) throws Exception {
        rpRepository.updateYnDelRp(seqRp);
    }

    @Transactional
    public void updateCommentRp(Long seqRp, String commentRp) throws Exception {
        rpRepository.updateCommentRp(seqRp,commentRp);
    }

}
