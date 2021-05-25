package practiceboard.one.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import practiceboard.one.domain.item.Item;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@NoArgsConstructor
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;
    private int orderPrice;
    private int count;

    public OrderItem (Item item,int orderPrice,int count){
        this.item=item;
        this.orderPrice=orderPrice;
        this.count=count;
    }
    public void connectOrder(Order order){
        this.order=order;
    }
    //생성 메서드
    public static OrderItem createOrderItem(Item item,int orderPrice,int count){
        OrderItem orderItem=new OrderItem(item,orderPrice,count);
        //주문을 한 수량 만큼 재고를 마이너스 해줌
        item.removeStock(count);
        return orderItem;
    }
    //주문 취소로 재고 수량 되돌리기
    public void cancel(){
        getItem().addStock(count);
    }
    //조회를 위한 것
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
