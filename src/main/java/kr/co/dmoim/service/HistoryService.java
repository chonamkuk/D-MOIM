package kr.co.dmoim.service;

import kr.co.dmoim.dto.HistoryDto;
import kr.co.dmoim.domain.repository.HistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@AllArgsConstructor
@Service
public class HistoryService {
    private HistoryRepository historyRepository;

    public void save(HistoryDto historyDto) {
        historyRepository.save(historyDto.toEntity());
    }

}
