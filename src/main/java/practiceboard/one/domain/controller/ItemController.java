package practiceboard.one.domain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import practiceboard.one.domain.item.Book;
import practiceboard.one.domain.item.Item;
import practiceboard.one.service.ItemService;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    @GetMapping(value = "items/new")
    public String createItemBook(Model model){
        model.addAttribute("form",new BookForm());
        return "items/createItemForm";
    }

    @PostMapping(value = "items/new")
    public String create(BookForm form){
        Item book=Book.bookCreate(form.getId(),form.getName(),form.getPrice(),
                form.getStockQuantity(),form.getAuthor(),form.getIsbn());
        itemService.save(book);
        return "redirect:/items";
    }

    @GetMapping(value = "items")
    public String list(Model model){
        model.addAttribute("forms",itemService.findAll());
        return "items/itemList";
    }

    @GetMapping(value = "items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId")Long itemId, Model model){
        Book book=(Book)itemService.findOne(itemId);

        //화면에 보낼 엔티티의 값을 form 에 넣어서 보내 주어 수정하도록함
        BookForm form=new BookForm();
        form.setId(book.getId());
        form.setPrice(book.getPrice());
        form.setStockQuantity(book.getStockQuantity());
        model.addAttribute("form",form);
        return "items/updateItem";
    }

    @PostMapping(value = "items/{itemId}/edit")
    public String update(@PathVariable("itemId")Long itemId, @ModelAttribute("form") BookForm form){
        itemService.modifyItem(itemId,form.getStockQuantity(),form.getPrice());
        return "redirect:/items";
    }
}
