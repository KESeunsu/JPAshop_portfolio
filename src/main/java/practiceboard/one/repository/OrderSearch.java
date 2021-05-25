package practiceboard.one.repository;

import lombok.Getter;
import lombok.Setter;
import practiceboard.one.domain.OrderStatus;

@Getter @Setter
public class OrderSearch {
    //검색 화면에 클릭하는 조건들 where=?(name)and?(status)
    private String memberName;
    private OrderStatus status;


}
