package practiceboard.one.domain.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {


    private Long id;/*아이템의 수정을 하기 위해 아이디 값이 필요*/
    private String name;
    private int price;
    private int stockQuantity;
/*위에는 item의 공통속성
* 아래의 두개는 book만 가지는 속성*/
    private String author;
    private String isbn;

}
