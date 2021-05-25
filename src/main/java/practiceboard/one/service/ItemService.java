package practiceboard.one.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practiceboard.one.domain.item.Item;
import practiceboard.one.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
//트랜잭션을 서비스에서 해놓게 되면 여러 리포지토리를 한 곳에서 같이 사용하여 트랜잭션을 이용할수있다는 장점이 있다
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public void save(Item item){
        itemRepository.save(item);
    }

    //위에보다 이렇게 만드는게 좋은 방법
    public void modifyItem(Long id,int stock,int price){
        Item findItem=itemRepository.findOne(id);
        findItem.change(stock,price);
    }
    @Transactional(readOnly = true)
    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }



/*public void update(Item item, BookForm form){
        Item findItem=itemRepository.findOne(item.getId());
        findItem.change(form.getStockQuantity(), form.getPrice());
    }
*/
}
