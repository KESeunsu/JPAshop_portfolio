package practiceboard.one.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import practiceboard.one.domain.item.Item;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }

    public void save(Item item){
        if(item.getId()==null){
            em.persist(item);
        }else{
            em.merge(item);
        }
    }
    public Item findOne(Long itemId){
        return em.find(Item.class,itemId);
    }



}
