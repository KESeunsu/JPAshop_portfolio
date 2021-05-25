package practiceboard.one.domain.item;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
@NoArgsConstructor
public class Book extends Item{
    private String author;
    private String isbn;

    public Book(Long id,String name,int price,int stock,String author,String isbn) {
        super(id,name,price,stock);
        this.author = author;
        this.isbn=isbn;
    }

    public static Item bookCreate(Long id,String name,int price,int stock,String author,String isbn){
        Item book=new Book(id,name,price,stock,author,isbn);
        return book;
    }


}
