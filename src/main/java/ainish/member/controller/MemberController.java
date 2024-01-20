package ainish.member.controller;

import ainish.member.dto.loginDto;
import ainish.member.dto.signUpDto;
import ainish.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("")
    ResponseEntity login(@Validated @RequestBody loginDto dto){
        long response = memberService.login(dto);

        if(response == -1){
            log.info("비밀번호 오류");
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
