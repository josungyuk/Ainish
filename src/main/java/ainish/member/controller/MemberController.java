package ainish.member.controller;

import ainish.member.dto.signUpDto;
import ainish.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("")
    ResponseEntity signUp(@Validated @RequestBody signUpDto dto){
        long response = memberService.signUp(dto);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
