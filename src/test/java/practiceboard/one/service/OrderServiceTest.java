package practiceboard.one.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import practiceboard.one.domain.Address;
import practiceboard.one.domain.Member;
import practiceboard.one.domain.Order;
import practiceboard.one.domain.OrderStatus;
import practiceboard.one.domain.item.Book;
import practiceboard.one.exception.NotEnoughStockException;
import practiceboard.one.repository.OrderRepository;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired private EntityManager em;
    @Autowired private OrderService orderService;
    @Autowired private OrderRepository orderRepository;
    /*@Test
    public void 상품주문() throws Exception {
        //given
        Member member=new Member();
        member.setName("memberA");
        member.setAddress(new Address("대구","동성로","123-123"));
        em.persist(member);

        Book book=new Book();
        book.setName("spring JPA");
        book.setPrice(20000);
        book.setStockQuantity(10);
        em.persist(book);

        int count=2;

        //when
        Long orderId=orderService.order(member.getId(),book.getId(),count);
        //then
        Order findOrder=orderRepository.findOne(orderId);
        Assert.assertEquals("주문상태는 ORDER여야한다",findOrder.getOrderStatus(), OrderStatus.ORDER);
        Assert.assertEquals("주문가격",book.getPrice()*count,findOrder.getTotalPrice());
        Assert.assertEquals("남은재고",8,book.getStockQuantity());


    }
    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과(){
        Member member=new Member();
        member.setName("memberA");
        member.setAddress(new Address("대구","동성로","123-123"));
        em.persist(member);

        Book book=new Book();
        book.setName("spring JPA");
        book.setPrice(20000);
        book.setStockQuantity(10);
        em.persist(book);

        int count=11;

        Long orderId=orderService.order(member.getId(),book.getId(),count);

        Assert.fail("수량 부족 예외가 떠야함");

    }
    @Test
    public void 주문취소(){
        Member member=new Member();
        member.setName("memberA");
        member.setAddress(new Address("대구","동성로","123-123"));
        em.persist(member);

        Book book=new Book();
        book.setName("spring JPA");
        book.setPrice(20000);
        book.setStockQuantity(10);
        em.persist(book);

        int count=2;

        //when
        Long orderId=orderService.order(member.getId(),book.getId(),count);

        orderService.cancelOrder(orderId);
        Order findOrder=orderRepository.findOne(orderId);

        Assert.assertEquals("취소가 된 상태",findOrder.getOrderStatus(),OrderStatus.CANCEL);

        Assert.assertEquals("재고가 증가해야함",book.getStockQuantity(),10);

*/
    }
}