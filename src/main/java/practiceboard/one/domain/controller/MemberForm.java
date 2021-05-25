package practiceboard.one.domain.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {
    @NotEmpty(message = "회원의 이름 입력은 필수항목입니다.")
    public String name;
    private String city;
    private String street;
    private String zipcode;

}
