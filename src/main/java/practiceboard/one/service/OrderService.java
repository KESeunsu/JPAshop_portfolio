package practiceboard.one.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practiceboard.one.domain.Delivery;
import practiceboard.one.domain.Member;
import practiceboard.one.domain.Order;
import practiceboard.one.domain.OrderItem;
import practiceboard.one.domain.item.Item;
import practiceboard.one.repository.ItemRepository;
import practiceboard.one.repository.MemberRepository;
import practiceboard.one.repository.OrderRepository;
import practiceboard.one.repository.OrderSearch;

import java.util.List;

@Service
@Transactional//트랜잭션을 서비스에서 해놓게 되면 여러 리포지토리를 한 곳에서 같이 사용하여 트랜잭션을 이용할수있다는 장점이 있다
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    //주문
    public Long order(Long memberId, Long itemId, int count){
        //Member 엔티티조회
        Member member=memberRepository.findOne(memberId);
        //배송정보
        Delivery deliver=new Delivery();
        deliver.setAddress(member.getAddress());
        //Item 엔티티조회
        Item item=itemRepository.findOne(itemId);
        //주문상품 생성
        OrderItem orderItem=OrderItem.createOrderItem(item,item.getPrice(),count);
        //주문생성
        Order order=Order.createOrder(member,deliver,orderItem);
        //주문저장
        orderRepository.save(order);//delivery,orderItem을 따로 저장하는 것이 없어도 된다 casecade설정을 해두었기때문이다

        return order.getId();
    }
    //취소
    public void cancelOrder(Long orderId){
        Order order=orderRepository.findOne(orderId);
        order.cancel();

    }
    //검색
    public List<Order> findOrders(OrderSearch orderSearch){
        List<Order> orders = orderRepository.findAllByString(orderSearch);
        return orders;
    }
}
