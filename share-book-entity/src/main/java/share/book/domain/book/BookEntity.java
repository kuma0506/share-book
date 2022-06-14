package share.book.domain.book;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import share.book.infra.jpa.BaseEntity;

/**
 * BookEntity generated by hbm2java
 */
@Entity
@Table(name="book"
)
public class BookEntity extends BaseEntity implements java.io.Serializable {


     private String id;
     private String name;
     private String author;
     private String dateOfIssue;
     private Set<ProductEntity> products = new HashSet<ProductEntity>(0);

    public BookEntity() {
    }

	
    public BookEntity(String id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }
    public BookEntity(String id, String name, String author, String dateOfIssue, Set<ProductEntity> products) {
       this.id = id;
       this.name = name;
       this.author = author;
       this.dateOfIssue = dateOfIssue;
       this.products = products;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false, length=22)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    
    @Column(name="name", nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="author", nullable=false, length=100)
    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    
    @Column(name="date_of_issue", length=10)
    public String getDateOfIssue() {
        return this.dateOfIssue;
    }
    
    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="book")
    public Set<ProductEntity> getProducts() {
        return this.products;
    }
    
    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }




}

