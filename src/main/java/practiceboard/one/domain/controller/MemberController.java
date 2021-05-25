package practiceboard.one.domain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import practiceboard.one.domain.Address;
import practiceboard.one.domain.Member;
import practiceboard.one.service.MemberService;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping(value = "members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm" ,new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping(value = "members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result){
        if(result.hasErrors()){
            return "members/createMemberForm";
        }//@valid으로 memberForm의 유효성 검사를 하고 설정해놓은 검사에 맞지 않으면
        // result에 에러를 담게된다
        Member member=new Member();
        member.setName(memberForm.getName());
        Address address=new Address(memberForm.getCity(), memberForm.getStreet(),memberForm.getZipcode());
        //address 임베디드 타입-생서자를 통한다
        member.setAddress(address);
        memberService.join(member);
        return "redirect:/members";
    }
    @GetMapping(value = "members")
        public String memberList(Model model){
            model.addAttribute("members",memberService.findMembers());
            return "members/memberList";
        }
    }

