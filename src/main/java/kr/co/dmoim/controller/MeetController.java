package kr.co.dmoim.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/meet")
public class MeetController {

    @GetMapping("/write.do")
    public String write() {
        return "meet/write";
    }
}
