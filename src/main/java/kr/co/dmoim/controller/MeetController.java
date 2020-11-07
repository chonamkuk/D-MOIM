package kr.co.dmoim.controller;

import kr.co.dmoim.domain.code.MeetStat;
import kr.co.dmoim.dto.*;
import kr.co.dmoim.service.MeetService;
import kr.co.dmoim.util.AES256Util;
import kr.co.dmoim.util.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/meet")
public class MeetController {
    @Autowired
    AES256Util aes;

    private MeetService meetService;

    /**
     * 모임 작성페이지 이동
     * @return
     */
    @GetMapping("/write.do")
    public String write() throws Exception {
        return "meet/write";
    }

    /**
     * 모임 작성
     * @param meetDto
     * @return
     * @throws Exception
     */
    @PostMapping("/write.do")
    public String write(MeetDto meetDto, @RequestParam(value = "idAccount", required = false) List<String> idAccounts, @AuthenticationPrincipal AccountDto accountSession) throws Exception {
        meetDto.setStatMeet(MeetStat.A);
        meetDto.setYnDel("N");
        meetDto.setRegDt(LocalDateTime.now());
        meetDto.setPasswordMeet(aes.encrypt(meetDto.getPasswordMeet()));
//        idAccounts.add(accountSession.getIdAccount()); // todo: 로그인 구현 후 작성자 아이디 추가

        System.out.println(meetDto);
        Long seqMeet = meetService.save(meetDto, idAccounts, accountSession);
        return "redirect:/meet/detail.do?seqMeet="+seqMeet;
    }

    /**
     * 모이 상세조회
     * @param model
     * @param meetDto
     * @param searchDto
     * @param pageable
     * @param response
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/detail.do")
    public String detail(Model model, MeetDto meetDto, SearchDto searchDto, final PageRequest pageable
            , HttpServletResponse response, HttpServletRequest request) throws Exception {

        MeetDto resultDto = meetService.getDetail(meetDto.getSeqMeet());

        model.addAttribute("resultDto", resultDto);
        model.addAttribute("searchDto", searchDto);
        model.addAttribute("pagingResult", pageable);

        return "meet/detail";
    }


}
