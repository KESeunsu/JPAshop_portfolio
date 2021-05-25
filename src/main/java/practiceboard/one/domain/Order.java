package practiceboard.one.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems=new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문 상태
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime orderDate; //hibernate가 시간지원해줌

    /*양방향 관련 메서드*/
    /*주문(order) 회원(member) 서로 연결*/
    public void setMember(Member member){
        this.member=member;
        member.getOrders().add(this);
    }
    /*주문한 orderItem,order 서로 연결*/
    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.connectOrder(this);

    }
    /*주문(order)을 한다면 배송정보가(deliver) 추가됨*/
    public void setDelivery(Delivery deliver){
        this.delivery=deliver;
        deliver.setOrder(this);
    }

    /*생성 메서드*/
    public static Order createOrder(Member member,Delivery delivery,OrderItem... orderItems){
        Order order=new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem:orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }
    //비지니스 로직 - 주문취소
    public void cancel(){
        if(delivery.getStatus()==DeliveryStatus.COMP){
            throw new IllegalStateException("상품이 이미 배송완료가 되었습니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem:orderItems){
            orderItem.cancel();
        }
    }

    /*나의 포폴에 구현아직 안됨*/
    public int getTotalPrice(){
        int totalPrice=0;
        for(OrderItem orderItem: orderItems){
            totalPrice+=orderItem.getTotalPrice();
        }
        return totalPrice;
    }

}
