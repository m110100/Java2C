package HibernatePartOne;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private int productId;
    @Column(name = "product_name",nullable = false)
    private String name;
    @Column(name = "price",nullable = false)
    private int price;

    @OneToMany(mappedBy = "product")
    public List<CustomerProduct> customerProduct;

    public Product(){}

    public Product(String name, int price){
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return productId;
    }

    public void setId(int id) {
        this.productId = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public  String toString(){
        return this.getName();
    }
}
