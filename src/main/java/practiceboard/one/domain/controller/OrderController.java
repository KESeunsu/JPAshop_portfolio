package practiceboard.one.domain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practiceboard.one.domain.Order;
import practiceboard.one.repository.OrderSearch;
import practiceboard.one.service.ItemService;
import practiceboard.one.service.MemberService;
import practiceboard.one.service.OrderService;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ItemService itemService;
    private final MemberService memberService;


    @GetMapping(value = "order")
    public String createOrder(Model model){
        model.addAttribute("members",memberService.findMembers());
        model.addAttribute("items",itemService.findAll());
        return "order/orderForm";
    }

    @PostMapping(value = "order")
    public String createOrderForm(@RequestParam("memberId")Long memberId,
                                  @RequestParam("itemId")Long itemId,
                                  @RequestParam("count") int count){
        orderService.order(memberId,itemId,count);
        return "redirect:/orders";
    }
    //주문 리스트에서 검색
    @GetMapping("orders")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model){
        //@ModelAttribute("orderSearch")=model.addAttribute("orderSearch",orderSearch)
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders",orders);
        return "order/orderList";
    }
    @PostMapping("orders/{orderId}/cancel")
    public String modifyOrder(@PathVariable("orderId")Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }

   /* @GetMapping("orders/{memberName}/{status}")
    public String search(@PathVariable(""))*/

}
