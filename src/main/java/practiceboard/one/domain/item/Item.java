package practiceboard.one.domain.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import practiceboard.one.domain.Category;
import practiceboard.one.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor
public abstract class Item {
    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;
    private String name;
    private int stockQuantity;
    private int price;
    @ManyToMany(mappedBy = "items")
    private List<Category> categories  =new ArrayList<>();

    public Item(Long id,String name, int stockQuantity, int price) {
        this.id=id;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }
    //더티 채킹을 위한 수정용
    public void change(int stock,int price){
        this.stockQuantity=stock;
        this.price=price;
    }
    /*주문하고 취소할때 재고를 다시 되돌리는 메서드*/
    public void addStock(int stockQuantity){
        this.stockQuantity+=stockQuantity;
    }
    /*주문을 한다면 재고가 그만큼 카운트*/
    public void removeStock(int orderQuantity){
        int restStock=stockQuantity-orderQuantity;
        if(restStock<=0){
            throw new NotEnoughStockException("need more Stock");
        }
        stockQuantity=restStock;
    }


}
